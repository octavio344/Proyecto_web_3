package com.edu.iua.eventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.edu.iua.model.Orden;
import com.edu.iua.rest.Constantes;


@Component
public class OrdenEventListener implements ApplicationListener<OrdenEvent>{

	@Autowired
	private SimpMessagingTemplate wSock;
	
	@Override
	public void onApplicationEvent(OrdenEvent event) {

		if (event.getSource() instanceof Orden) {
			
			if(event.getTipo().equals(OrdenEvent.Tipo.TEMPERATURA_MAXIMA))
				manejaEventoExcesoTemperatura((Orden) event.getSource());
			else if(event.getTipo().equals(OrdenEvent.Tipo.CAPACIDAD_NOVENTA_PORCIENTO))
				manejaEventoCapacidadAlNoventaPorciento((Orden) event.getSource());
			else if(event.getTipo().equals(OrdenEvent.Tipo.PRESET_EXCEDIDO))
				manejaEventoCapacidadExcedePreset((Orden) event.getSource());
			
		}
	}
	
	@Autowired
	private JavaMailSender emailSender;
	
	private void manejaEventoExcesoTemperatura(Orden orden) {
		String mensaje = String.format("La temperatura de carga de la orden " + orden.getNroOrden() + " ha excedido el limite, su temperatura actual es de " + orden.getTemperatura() + "°C" + "TYPE=excesoTemp");
		
		wSock.convertAndSend(Constantes.TOPIC_SEND_WEBSOCKET_GRAPH,	mensaje);
		
		String subject = "Exceso de temperatura en la orden " + orden.getNroOrden();
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("finaliw3aig@gmail.com");
			message.setTo("alanalberino@gmail.com");
			message.setSubject(subject);
			message.setText(mensaje.split("TYPE")[0]);
			emailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void manejaEventoCapacidadAlNoventaPorciento(Orden orden) {
		String mensaje = "La capacidad de carga de la orden " + orden.getNroOrden() + " ha superado el 90%, por favor verifique que se finalice la carga al alcanzar el preset" + "TYPE=90preset";
		
		wSock.convertAndSend(Constantes.TOPIC_SEND_WEBSOCKET_GRAPH,	mensaje);
	}
	
	private void manejaEventoCapacidadExcedePreset(Orden orden) {
		String mensaje = "La capacidad de carga de la orden " + orden.getNroOrden() + " ha superado el preset, por favor comunique que se debe finalizar la carga" + "TYPE=presetSuperado";
		
		wSock.convertAndSend(Constantes.TOPIC_SEND_WEBSOCKET_GRAPH,	mensaje);
	}
}
