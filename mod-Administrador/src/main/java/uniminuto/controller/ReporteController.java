package uniminuto.controller;

import java.text.DateFormat;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import groovyjarjarcommonscli.ParseException;
import uniminuto.model.Reporte;
import uniminuto.service.ReporteService;
import uniminuto.service.loginService;

@Controller
@RequestMapping("/admin")
public class ReporteController {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ReporteController.class);
	
	@Autowired
	private ReporteService reposervice;
	
	@GetMapping("/reporte")
	public String main(org.springframework.ui.Model model, 
						@RequestParam(name = "errorareas", required = false) String errorFormAreas,
						@RequestParam(name = "errornivel", required = false) String errorFormNivel,
						@RequestParam(name = "errorfechasini", required = false) String errorFechaini,
						@RequestParam(name = "errorfechasfin", required = false) String errorFechafin,
						@RequestParam(name = "errorfechasvalid", required = false) String errorFechaValid) {
			model.addAttribute("errorareas", errorFormAreas);
			model.addAttribute("errornivel", errorFormNivel);
			model.addAttribute("errorfechasini", errorFechaini);
			model.addAttribute("errorfechasfin", errorFechafin);
			model.addAttribute("errorfechasvalid", errorFechaValid);
			log.error("El error areas enviado es:" + errorFormAreas );
			log.error("El error niveless enviado es:" + errorFormNivel );
			log.error("El error fechasss enviado es:" + errorFechaini );
			log.error("El error fechasss final enviado es:" + errorFechafin );
			
			model.addAttribute("reporteUniminuto", new Reporte());
		
		return "fragments/sections/formulario-filtros";
	}
	
	@PostMapping("/generarepo")
	public String generateReport(@ModelAttribute(name = "reporteUniminuto") Reporte reporte, Model model) throws Exception{
		
		if (reporte.getFechainitemp() == null || reporte.getFechainitemp().isEmpty()) {
			return "redirect:/admin/reporte?errorfechasini=inicio";
		}
		if (reporte.getFechafintemp() == null || reporte.getFechafintemp().isEmpty()) {
			return "redirect:/admin/reporte?errorfechasfin=final";
		}
		
		if (reporte.getFechainitemp() != null && reporte.getFechafintemp() != null) {
			log.info("fechas ingresadas");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date fechaInicial = formatter.parse(reporte.getFechainitemp());
				Date fechaFinal = formatter.parse(reporte.getFechafintemp());
			
			if (fechaInicial.before(fechaFinal)) {
				log.info("Las fechas se pueden consultar");
			}else {
				log.error("Error la fecha inicial no debe superar a la final ");
				return "redirect:/admin/reporte?errorfechasvalid=error";
			}
			
		}
		
		if (reporte.getAreasUniminuto() == null || reporte.getNivelSatisfaccion() == null) {
			if (reporte.getAreasUniminuto() == null ) {
				return "redirect:/admin/reporte?errorareas=areas";
			}
			if (reporte.getNivelSatisfaccion() == null) {
				return "redirect:/admin/reporte?errornivel=nivel";
			}
		}
		
		log.info("La respuesta del servicio es la siguiente: \n" + reposervice.sendReporte(reporte));
		
		model.addAttribute("registros", reposervice.sendReporte(reporte));
		
		return "fragments/sections/lista-areas";
	}	

}
