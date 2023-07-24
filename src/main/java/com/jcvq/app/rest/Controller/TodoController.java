package com.jcvq.app.rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jcvq.app.rest.Repository.TodoRepository;
import com.jcvq.app.rest.model.Task;

//Especificacion de arquitectura
@RestController
public class TodoController {
	
	//Instaciar el repositorio dentro de la clase
	@Autowired
	private TodoRepository todoRepository;
	//Leer datos del servidor
	
	@GetMapping(value = "/")
	public String holaMundo() {
		return "Hola Mundo";
	}
	
	//METODOS HTTP
	
	/*METODO GET PARA TRAER LA LISTA DE TAREAS
	 * 1. Con la anotacion GetMapping traeremos todas las tareas
	 * 2. Creamos un metodo tipo lista que retornara todo el repositorio */
	@GetMapping(value = "/tasks")
	public List <Task> getTasks(){
		return todoRepository.findAll();
	}
	
	/*
	 * Metodo POST SALVAR LA TAREA
	 * 1. Como parametro traemos la tarea
	 * 2. traemos la instanciacion del repositorio y salvamos la tarea
	 * 3. retornamos un string para saber si el proceso finalizo
	 */
	@PostMapping(value = "/savetasks")
	public String saveTask(@RequestBody Task task) {
		todoRepository.save(task);
		return "Saved task";
	}
	/*
	 * METODO MODIFICAR la tarea
	 * 1. Como parametros recibira el id y la tarea
	 * 2. Buscara por id y lo obtendra
	 * 3. Modificara la tarea y la descripcion 
	 * 4. Salvara estos cambios.
	 * 
	 */
	@PutMapping(value = "/update/{id}")
	public String updateTask(@PathVariable long id, @RequestBody Task task ) {
		Task updateTask = todoRepository.findById(id).get();
		updateTask.setTarea(task.getTarea());
		updateTask.setDescripcion(task.getDescripcion());
		
		todoRepository.save(updateTask);
		return "Update Task";
	}
	/*
	 * METODO DELETE PARA BORRAR LAS TAREAS
	 */
	@DeleteMapping(value = "delete/{id}")
	public String deleteTask(@PathVariable long id) {
		Task deletedTask = todoRepository.findById(id).get();
		todoRepository.delete(deletedTask);
		return "Deleted Task";
	}
	
}
