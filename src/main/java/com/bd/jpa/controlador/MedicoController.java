package com.bd.jpa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bd.jpa.modelo.TblMedico;
import com.bd.jpa.servicio.IMedicoServicio;

@Controller
@RequestMapping("/vistas")
public class MedicoController {

 @Autowired
 private IMedicoServicio imedicoservicio;

 @GetMapping("ListadoMedico")
 public String listadoMedico(Model modelo) {
  List<TblMedico> listaMedicos = imedicoservicio.ListadoMedico();
  modelo.addAttribute("medicos", listaMedicos);
  return "listadoMedico";
 }

 @GetMapping("registrarMedico")
 public String mostrarFormularioRegistro(Model modelo) {
  modelo.addAttribute("medico", new TblMedico());
  return "registrarMedico";
 }

 @PostMapping("registrarMedico")
 public String guardarMedico(@ModelAttribute("medico") TblMedico medico) {
  imedicoservicio.RegistrarMedico(medico);
  return "redirect:/vistas/ListadoMedico";
 }
}
