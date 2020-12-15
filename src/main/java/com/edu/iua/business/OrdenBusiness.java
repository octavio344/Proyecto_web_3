package com.edu.iua.business;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.edu.iua.business.exception.BusinessException;
import com.edu.iua.business.exception.NotFoundException;
import com.edu.iua.business.exception.WrongStateException;
import com.edu.iua.eventos.OrdenEvent;
import com.edu.iua.model.Camion;
import com.edu.iua.model.Chofer;
import com.edu.iua.model.Cliente;
import com.edu.iua.model.ConciliacionDTO;
import com.edu.iua.model.DetalleOrden;
import com.edu.iua.model.Orden;
import com.edu.iua.model.Producto;
import com.edu.iua.model.persistence.CamionRepository;
import com.edu.iua.model.persistence.ChoferRepository;
import com.edu.iua.model.persistence.ClienteRepository;
import com.edu.iua.model.persistence.DetalleOrdenRepository;
import com.edu.iua.model.persistence.OrdenRepository;
import com.edu.iua.model.persistence.ProductoRepository;
import com.edu.iua.rest.Constantes;

@Service
public class OrdenBusiness implements IOrdenBusiness {

	@Autowired
	private OrdenRepository ordenDAO;
	
	@Autowired
	private DetalleOrdenRepository detalleDAO;
	
	@Autowired
	private ClienteRepository clienteDAO;
	
	@Autowired
	private ChoferRepository choferDAO;
	
	@Autowired
	private ProductoRepository productoDAO;
	
	@Autowired
	private CamionRepository camionDAO;
	
	private float temperaturaMaxima = 80;
	
	@Override
	public List<Orden> listAll() throws BusinessException {
		 
		try {
			return ordenDAO.findAll();
		}catch(Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Orden findById(Long id) throws NotFoundException, BusinessException {
		Optional<Orden> op;
		
		try {
			op = ordenDAO.findById(id);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
		
		if(!op.isPresent()) {
			throw new NotFoundException("No se encontro ninguna orden con el siguiente identificador: "+ id);
		}
		
		return op.get();
	}

	@Override
	public void delete(Long id) throws BusinessException, NotFoundException {
		try {		
			ordenDAO.deleteById(id);
			}catch (EmptyResultDataAccessException e1) {
				throw new NotFoundException("No se encuentra la orden con el identificador:" + id);
				}
			catch(Exception e) {
				throw new BusinessException(e);
			}
	}

	@Override
	public Orden add(Orden o) throws BusinessException,IllegalArgumentException {
		try {
			Optional<Chofer> opChofer;
			if(o.getChofer().getCodigoExterno()!=null)
				opChofer= choferDAO.findByCodigoExterno(o.getChofer().getCodigoExterno());
			else opChofer = choferDAO.findById(o.getChofer().getIdChofer());
			
			Optional<Cliente> opCliente;
			if(o.getCliente().getCodigoExterno()!=null)
				opCliente = clienteDAO.findByCodigoExterno(o.getCliente().getCodigoExterno());
			else opCliente = clienteDAO.findById(o.getCliente().getIdCliente());
			
			Optional<Camion> opCamion;
			if(o.getCamion().getCodigoExterno()!=null)
				opCamion = camionDAO.findByCodigoExterno(o.getCamion().getCodigoExterno());
			else opCamion = camionDAO.findById(o.getCamion().getIdCamion());
			
			Optional<Producto> opProducto;
			if(o.getProducto().getCodigoExterno()!=null)
				opProducto = productoDAO.findByCodigoExterno(o.getProducto().getCodigoExterno());
			else opProducto = productoDAO.findById(o.getProducto().getId());
			
			if(!opChofer.isPresent()) {
				choferDAO.save(o.getChofer());
			}
			
			if(!opCliente.isPresent()) {
				clienteDAO.save(o.getCliente());
			}
			
			if(!opCamion.isPresent()) {
				camionDAO.save(o.getCamion());
			}
			
			if(!opProducto.isPresent()) {
				productoDAO.save(o.getProducto());
			}
			
			o.setCamion(opCamion.get());
			o.setCliente(opCliente.get());
			o.setProducto(opProducto.get());
			o.setChofer(opChofer.get());
			o.setFechaRecepcion(new Date());
			
			return ordenDAO.save(o);
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		}
		catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	
	// Tirar exc si los datos estan mal
	
	@Override
	public Orden setearPesajeInicial(Orden o) throws BusinessException, NotFoundException, WrongStateException {
		
		Optional<Orden> opOrden;
		if(o.getCodigoExterno()!=null)
			opOrden = ordenDAO.findByCodigoExterno(o.getCodigoExterno());
		else opOrden = ordenDAO.findById(o.getNroOrden());
		
		if(!opOrden.isPresent()) {
			if(o.getCodigoExterno()!=null)
				throw new NotFoundException("No se encuentra la orden con el codigo externo =" + o.getCodigoExterno());
			else throw new NotFoundException("No se encuentra la orden con el ID = " + o.getNroOrden());
		}else {
			Orden or = opOrden.get();
			if(or.getEstado()==1) {
				if(o.getPesajeInicial()!= null || o.getPesajeInicial()>0) {
					or.setPesajeInicial(o.getPesajeInicial());
					or.setEstado(2);
					or.generatePsswd(); // Mostralo con logger en info del servidor
				}
				
				ordenDAO.save(or);
				
				return or;
				
			}else {
				
				throw new WrongStateException("La orden debe estar en estado 1 para utilizar este endpoint.");
			}
		}
		
	}

	@Override
	public Orden updateDetalle(Orden o) throws BusinessException, NotFoundException, WrongStateException {
		
		o.setFechaUltimoAlmacenamiento(new Date());
		
		if(o.getDensidad()==null || o.getDensidad()<0 || o.getDensidad() >1)
			throw new BusinessException("La densidad ingresada debe ser un valor entre 0 y 1.");
		if(o.getTemperatura()==null || o.getTemperatura() <0)
			throw new BusinessException("La temperatura ingresada es incorrecta.");
		if(o.getCaudal()==null || o.getCaudal() <0)
			throw new BusinessException("El caudal ingresado es incorrecto.");
		
		Optional<Orden> op;
		if(o.getCodigoExterno()!=null)
			op = ordenDAO.findByCodigoExterno(o.getCodigoExterno());
		else op = ordenDAO.findById(o.getNroOrden());
			
	    if(!op.isPresent()) {
	    	if(o.getCodigoExterno()!=null)
				throw new NotFoundException("No se encuentra la orden con el codigo externo =" + o.getCodigoExterno());
			else throw new NotFoundException("No se encuentra la orden con el ID = " + o.getNroOrden());
	    }  
	    
	    else try {
	    	Orden or = op.get();
	    	
	    	if(or.getEstado()!=2) {
	    		throw new WrongStateException("La orden debe estar en estado 2 para utilizar este servicio");
	    	}
	    	
	    	if(o.getMasaAcumulada()==null || o.getMasaAcumulada()<or.getMasaAcumulada())
	    		throw new BusinessException("La masa acumulada ingresada no puede ser menor a la anterior.");
	    	
	    	if(or.getMasaAcumulada()==0.0) {
	    		or.setFechaIProcesoCarga(new Date());
	    		or.setFechaUltimoAlmacenamiento(new Date());
				detalleDAO.save(new DetalleOrden(or,o.getMasaAcumulada(),o.getDensidad(),o.getTemperatura(),o.getCaudal(), new Date()));
	    	}	    	
	    	
	    	or.setCaudal(o.getCaudal());
			or.setTemperatura(o.getTemperatura());
			or.setMasaAcumulada(o.getMasaAcumulada());
			or.setDensidad(o.getDensidad());
	    	
	    	long diffInMillies = Math.abs(o.getFechaUltimoAlmacenamiento().getTime() - or.getFechaUltimoAlmacenamiento().getTime());
		    long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    	
			if(diff > Constantes.PERIODO_ALMACENAMIENTO) {
				detalleDAO.save(new DetalleOrden(or,o.getMasaAcumulada(),o.getDensidad(),o.getTemperatura(),o.getCaudal(), new Date()));
				or.setFechaUltimoAlmacenamiento(o.getFechaUltimoAlmacenamiento());
			}
			
			//Si no tiene la alarma encendida, verifico si hay que generar alguna alarma
			//Si hay una alarma encendida no entro ya que no se pueden generar dos alarmas en simultaneo
			if(!or.isTieneAlarmaEncendida()) {
				if(or.getMasaAcumulada()> (or.getPreset()*0.9)) {
					generaEvento(or, OrdenEvent.Tipo.CAPACIDAD_NOVENTA_PORCIENTO);
					or.setTieneAlarmaEncendida(true);
				}
				
				if(or.getMasaAcumulada()> or.getPreset()) {
					generaEvento(or, OrdenEvent.Tipo.PRESET_EXCEDIDO);
					or.setTieneAlarmaEncendida(true);
				}
				
				if (or.getTemperatura()>temperaturaMaxima) {
					generaEvento(or, OrdenEvent.Tipo.TEMPERATURA_MAXIMA);
					or.setTieneAlarmaEncendida(true);
				}
			}
			
			ordenDAO.save(or);
			return or;
			
		}catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Orden cerrarOrden(Orden o) throws BusinessException, NotFoundException, WrongStateException {
	
		Optional<Orden> optional;
		if(o.getCodigoExterno()!=null)
			optional = ordenDAO.findByCodigoExterno(o.getCodigoExterno());
		else optional = ordenDAO.findById(o.getNroOrden());
		
		if(!optional.isPresent()) {
			if(o.getCodigoExterno()!=null)
				throw new NotFoundException("No se encuentra la orden con el codigo externo =" + o.getCodigoExterno());
			else throw new NotFoundException("No se encuentra la orden con el ID = " + o.getNroOrden());
		}
			
		Orden or = optional.get();
		
		if(or.getEstado()==2) {
			
			or.setEstado(3);
			or.setFechaFProcesoCarga(new Date());
			return ordenDAO.save(or);
			
		}else {
			throw new WrongStateException("La orden debe estar en estado 2 para utilizar este servicio");
		}
	
	}

	@Override
	public ConciliacionDTO finalizar(Orden o) throws BusinessException, NotFoundException, WrongStateException {
		

		Optional<Orden> op;
		if(o.getCodigoExterno()!=null)	
			op = ordenDAO.findByCodigoExterno(o.getCodigoExterno());
		else op = ordenDAO.findById(o.getNroOrden());
		
		if(!op.isPresent()) {
			if(o.getCodigoExterno()!=null)
				throw new NotFoundException("No se encuentra la orden con el codigo externo =" + o.getCodigoExterno());
			else throw new NotFoundException("No se encuentra la orden con el ID = " + o.getNroOrden());
		}
		
		Orden or= op.get(); 
		
		if(or.getEstado()==3 && o.getPesajeFinal()!=null && o.getPesajeFinal()>or.getPesajeInicial()) {
			
			or.setPesajeFinal(o.getPesajeFinal());
			
			or.setEstado(4);
			
			or.setFechaRecepcionPesajeF(new Date());
			
			ordenDAO.save(or);
			
			return generarConciliacion(or);
			
		}else {
			throw new WrongStateException("La orden debe estar en estado 3 para utilizar este servicio");
		}

	}
	
	private ConciliacionDTO generarConciliacion(Orden or) {
		
		Double tempPromedio = 0.0;
		
		Double caudalPromedio = 0.0;
		
		Double densidadPromedio = 0.0;
		
		List<DetalleOrden> detalleOrden = or.getDetalleOrdenList();
		
		int tamano = detalleOrden.size();
		
		for(DetalleOrden da: detalleOrden) {
			tempPromedio += da.getTemperaturaProducto();
			caudalPromedio += da.getCaudalProducto();
			densidadPromedio += da.getDensidadProducto();
		}
		
		tempPromedio /= tamano;
		caudalPromedio /= tamano;
		densidadPromedio /= tamano;
		
		return new ConciliacionDTO(or.getPesajeInicial(),or.getPesajeFinal(),or.getMasaAcumulada(),tempPromedio,caudalPromedio,densidadPromedio); 
	}
	
	@Override
	public Orden findByCodigoExterno(String c) throws NotFoundException, BusinessException {
		
		Optional<Orden> op=ordenDAO.findByCodigoExterno(c);
		
		if(!op.isPresent()) {
			throw new NotFoundException("No se encuentra la orden con el codigo externo =" + c);
		}
		
		return op.get();
	}
	
		@Override 
		public ConciliacionDTO getConciliacion(Long id) throws BusinessException, NotFoundException, WrongStateException{
		
		Optional<Orden> op;
		op = ordenDAO.findById(id);
			
		if(!op.isPresent()) {
			throw new NotFoundException("No se encuentra la orden con el ID = " + id);
		}
		
		Orden or = op.get();
		
		if(or.getEstado()!=4) {
			throw new WrongStateException("La orden debe estar en estado 4 para utilizar este servicio");
		}
		
		return generarConciliacion(or);
		
	}
	
	@Override public ConciliacionDTO getConciliacion(String codigoExterno) throws BusinessException, NotFoundException, WrongStateException{
		
		Optional<Orden> op;
		op = ordenDAO.findByCodigoExterno(codigoExterno);
			
		if(!op.isPresent()) {
			throw new NotFoundException("No se encuentra la orden con el Codigo Externo = " + codigoExterno);
		}
		
		Orden or = op.get();
		
		if(or.getEstado()!=4) {
			throw new WrongStateException("La orden debe estar en estado 4 para utilizar este servicio");
		}
		
		return generarConciliacion(or);
		
	}
	
	@Autowired
	private ApplicationEventPublisher appEventPublisher;

	private void generaEvento(Orden orden, OrdenEvent.Tipo tipo) {
		appEventPublisher.publishEvent(new OrdenEvent(orden, tipo));
	}

	@Override
	public Orden anularOrden(Long id) throws BusinessException, NotFoundException {
		Optional<Orden> op= ordenDAO.findById(id);
		
		
		if(!op.isPresent()) {
			throw new NotFoundException("No se encontro la orden con el nro de orden: "+id);
		}
		
		Orden or= op.get();
		
		or.setAnulado(true);
		
		return ordenDAO.save(or);
	}
	
	@Autowired
	private SimpMessagingTemplate wSock;

	@Override
	public void pushOrderData() {
		try {
			wSock.convertAndSend(Constantes.TOPIC_SEND_WEBSOCKET_GRAPH, ordenDAO.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void cambiarUmbralTemperatura(Float temp) {
		temperaturaMaxima = temp;
	}


}
