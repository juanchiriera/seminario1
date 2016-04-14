package controlador;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hbt.HibernateUtil;
import view.DescuentoV;
import view.EmpleadoV;
import view.InasistenciaV;
import view.SueldoV;
import negocio.Cargo;
import negocio.Clase;
import negocio.ConCargo;
import negocio.Empleado;
import negocio.Escuela;
import negocio.Licencia;
import negocio.LicenciaEmpleado;
import negocio.Liquidacion;
import negocio.Novedad;
import negocio.SinCargo;
import negocio.Usuario;
import srv.CargoSRV;
import srv.ClaseSRV;
import srv.EmpleadoSRV;
import srv.EscuelaSRV;
import srv.LicenciaSRV;
import srv.NovedadSRV;
import srv.UsuarioSRV;

public class Sistema {

	// Declaro los vectores
	private Collection<Empleado> empleados;
	private Collection<Novedad> novedades;
	private Collection<Licencia> licencias;
	private Collection<Escuela> escuelas;
	private Collection<Clase> clases;
	private Collection<Liquidacion> liquidaciones;
	private Collection<SinCargo> sinCargos;
	private Collection<ConCargo> conCargos;
	private Collection<Cargo> cargos;
	private Collection<LicenciaEmpleado> licenciasEmpleado;

	private static Sistema instancia;

	// Singleton para que exista solo una instancia
	public static Sistema getInstancia() {
		if (instancia == null)
			instancia = new Sistema();
		return instancia;
	}

	// Inicializo los vectores
	private Sistema() {
		empleados = new Vector<Empleado>();
		novedades = new Vector<Novedad>();
		licencias = new Vector<Licencia>();
		escuelas = new Vector<Escuela>();
		clases = new Vector<Clase>();
	}

	// --------------------------ADMINISTRACION DE EMPLEADOS----------------------//
	public String iniciarSesion(String nombreUsuario, String password) {
		Usuario usuario = buscarUsuario(nombreUsuario);
		if (usuario != null) {
			String token = usuario.validarContraseña(password);
			return token;
		}
		// TODO error de validacion
		return null;
	}

	public Usuario buscarUsuario(String nombreUsuario) {
		/* TODO busco el usuario por nombre en la BD */
		Usuario usuario = UsuarioSRV.buscarUsuario(nombreUsuario);
		return usuario;
	}

	// Para filtrar la busqueda por alguno de los campos
	public List<Empleado> buscarDocentes(String apellido, String nombre, String cuil, String dni, String materia,
			String curso, String division, boolean estado) {
		List<Empleado> empleadosEncontrados = EmpleadoSRV.buscarEmpleados(apellido, nombre, cuil, dni, materia, curso,
				division, estado);
		// for(Empleado emp : empleados){
		// if(emp.sosDocente(apellido, nombre, cuil, dni, materia, curso,
		// division, estado))
		// empleadosEncontrados.add(emp);
		//
		// }
		return empleadosEncontrados;
	}

	// Para filtrar la busqueda por alguno de los campos
	public Collection<Clase> buscarClasesDeEmpleado(String dni) {
		SinCargo empleado = EmpleadoSRV.buscarEmpleadoSinCargo(dni);
		Collection<Clase> clases = empleado.getClases();
		// for(SinCargo emp : sinCargos){
		// if(emp.getDni().equals(dni))
		// clasesEncontradas.addAll(emp.getClases());
		// }
		return clases;
	}

	// Para buscar a uno en partcicular ingresando dni
	public Empleado buscarEmpleado(String dni) {
		Empleado empleado = EmpleadoSRV.buscarEmpleado(dni);
		return empleado;
	}

	public void altaClaseEmpleado(String dni, Clase clase, boolean estado) {
		SinCargo profesor = EmpleadoSRV.buscarEmpleadoSinCargo(dni);
		if(profesor==null){
			//TODO ARREGLAR!!
			ConCargo empleado = EmpleadoSRV.buscarEmpleadoConCargo(dni);
			profesor = new SinCargo(empleado);
			EmpleadoSRV.eliminarEmpleado(empleado);
		}
		if (!profesor.tenesClase(clase.getDivision(), clase.getCurso())) {
			profesor.getClases().add(clase);
			profesor.setEstado(true);
			EmpleadoSRV.grabarEmpleado(profesor);
//			Session session = HibernateUtil.getSessionFactory().openSession();
//			Transaction tr = session.beginTransaction();
//			session.update(profesor);
//			tr.commit();
//			session.close();

		} else {
			/** TODO El empleado ya tiene esa clase asignada **/
		}
	}

	// Para filtrar la busqueda por alguno de los campos
	public Collection<Cargo> buscarCargosEmpleado(String dni) {
		Collection<Cargo> cargosEncontrados = new Vector<Cargo>();
		Empleado empleado = buscarEmpleado(dni);
		cargosEncontrados = (Collection<Cargo>) ((ConCargo) empleado).getCargos();
		return cargosEncontrados;
	}

	public void asignarCargo(String dni, Cargo cargo) {
		ConCargo empleado = (ConCargo) EmpleadoSRV.buscarEmpleado(dni);
		boolean flag = true;
		for (Cargo cargoAux : empleado.getCargos()) {
			if (cargo.equals(cargoAux)) {
				flag = false;
			}
		}
		if (flag == true) {
			empleado.getCargos().add(cargo);
		}
		EmpleadoSRV.actualizarEmpleado(empleado);
		
	}

	public void altaEmpleado(String nombre, String apellido, String dni, String cuil, Date fechaDeNacimiento,
			int antiguedad, int nroEscuela) {
		Empleado empleado = buscarEmpleado(dni);
		if (empleado == null) {
			empleado = new ConCargo();

			empleado.setNombre(nombre);
			empleado.setApellido(apellido);
			empleado.setDni(dni);
			empleado.setCuil(cuil);
			empleado.setFechaDeNacimiento(fechaDeNacimiento);
			empleado.setAntiguedad(antiguedad);
			Date fechaHoy = new Date();
			empleado.setFechaDeIngreso(fechaHoy);

			Escuela escuela = buscarEscuela(nroEscuela);
			empleado.setEscuela(escuela);
			// Pongo el estado siempre en falso, cuando se le asigna una clase o
			// un cargo se lo pone en verdadero
			empleado.setEstado(false);
			EmpleadoSRV.grabarEmpleado(empleado);
		}
	}

	public void modificarEmpleado(String nombre, String apellido, String dni, String cuil, Date fechaDeNacimiento,
			int antiguedad, int nroEscuela, boolean estado) {
		/**
		 * TODO Había pensado un do while para que se ejecute y si alguno es
		 * nulo que vuelva tire el error, pero los int y los boolean no aceptan
		 * nulos. IDEAS?
		 */
		Empleado empleado = buscarEmpleado(dni);
		if (empleado != null) {
			empleado.setNombre(nombre);
			empleado.setApellido(apellido);
			empleado.setDni(dni);
			empleado.setCuil(cuil);
			empleado.setFechaDeNacimiento(fechaDeNacimiento);
			empleado.setAntiguedad(antiguedad);
			// ver de agregar el nombre correspondiente al numero de escuela
			empleado.getEscuela().setNro(nroEscuela);
			empleado.setEstado(estado);
			EmpleadoSRV.actualizarEmpleado(empleado);
		}
	}

	// Baja lógica
	public void eliminarEmpleado(String dni) {
		Empleado empleado = buscarEmpleado(dni);
		if (empleado != null && empleado.isEstado() == true)
			empleado.setEstado(false);
		EmpleadoSRV.actualizarEmpleado(empleado);
	}

	// No se si esto esta bien, fue lo que se me ocurrio para mostrar los datos
	// que pide la GUI013
	public InasistenciaV mostrarInasistencias(String dni) {
		Empleado emp = buscarEmpleado(dni);
		InasistenciaV mostrarDatos = null;
		if (emp != null) {
			mostrarDatos = new InasistenciaV(emp.getApellido(), emp.getNombre(), emp.getDni());
			List<Licencia> licenciasBd = LicenciaSRV.recuperarLicencias();
			Vector<LicenciaEmpleado> licenciasEmpleadoV = new Vector<LicenciaEmpleado>();
			for(Licencia licenciaBd : licenciasBd){
				licenciasEmpleadoV.add(new LicenciaEmpleado(licenciaBd.getTiempo(), licenciaBd) );
			}
			for (LicenciaEmpleado licencia : emp.getLicenciasEmpleados()) {
				LicenciaEmpleado licenciaEmpleadoAux = buscarLicenciaEmpleado(licenciasEmpleadoV, licencia.getLicencia());
				licenciaEmpleadoAux.setCantDisponible(licencia.getCantDisponible());
				
			}
			mostrarDatos.setLicencias(licenciasEmpleadoV);
		}
		return mostrarDatos;
	}

	private LicenciaEmpleado buscarLicenciaEmpleado(Vector<LicenciaEmpleado> licenciasEmpleadoV, Licencia licencia) {
		for (LicenciaEmpleado licenciaEmpleado : licenciasEmpleadoV) {
			if(licenciaEmpleado.getLicencia().getCodigo()==licencia.getCodigo())
				return licenciaEmpleado;
		}
		return null;
	}

	public float calcularSueldo(String dni, int mes) {
		SinCargo empleadoS = EmpleadoSRV.buscarEmpleadoSinCargo(dni);
		ConCargo empleadoC = EmpleadoSRV.buscarEmpleadoConCargo(dni);
		float sueldoTotal = 0;
		if (empleadoS != null) {
			float sueldoBruto = empleadoS.calcularSueldoSemanal() * cantidadSemandasMes(mes);
			sueldoTotal = sueldoBruto - calcularDescuento(dni, mes,0);
			return sueldoTotal;
		}
		if (empleadoC != null) {
			float sueldoBruto = empleadoC.calcularSueldo();
			float descuento = 0;
			for (Cargo cargo : empleadoC.getCargos()) {
				descuento += calcularDescuento(dni,mes,cargo.getSueldoBasico());
			}
			sueldoTotal = sueldoBruto - descuento;
			return sueldoTotal;
		}
		return 0; /** TODO mensaje error **/
	}

	public float calcularDescuento(String dni, int mes, float basico) {
		Empleado emp = buscarEmpleado(dni);
		float descuento = 0;
		if (emp != null) {
			 List<Novedad> novedadesEmpleado = emp.getNovedades();
			for (Novedad novedad : novedadesEmpleado ) {
				if (novedad.getFecha().getMonth()+1 == mes) 
					descuento += novedad.obtenerDescuentoTotal(basico);
			}
			return descuento;
		}
		return 0; /** TODO mensaje error **/
	}

	

	public Vector<EmpleadoV> mostrarEmpleadosPorEscuela(int nroEscuela) {
		Vector<EmpleadoV> mostrarDatos = new Vector<EmpleadoV>();
		;
		EmpleadoV datos = null;
		for (Empleado emp : empleados) {
			if (emp.getEscuela().getNro() == nroEscuela) {
				datos.setApellido(emp.getApellido());
				datos.setNombre(emp.getNombre());
				datos.setDni(emp.getDni());
				datos.setCuil(emp.getCuil());
				datos.setNroEscuela(emp.getEscuela().getNro());
				datos.setNombreEscuela(emp.getEscuela().getNombre());

				mostrarDatos.add(datos);
			}
		}
		return mostrarDatos;
	}

	// --------------------------ADMINISTRACION DE
	// LICENCIAS----------------------//

	// Para filtrar la busqueda por alguno de los campos
	public Vector<Licencia> buscarLicencias(String codigo, String tipo, String motivo) {
		Vector<Licencia> licenciasEncontradas = LicenciaSRV.recuperarLicencias(codigo, tipo, motivo);
		// for(Licencia licencia : licencias){
		// if(licencia.hayLicencias(codigo, tipo, motivo))
		// licenciasEncontradas.add(licencia);
		// }
		return licenciasEncontradas;
	}

	// Para buscar una sola licencia
	public Licencia buscarLicencia(int codigo) {
		Licencia licencia = LicenciaSRV.buscarLicencia(codigo);
		// for(Licencia licencia : licencias){
		// if(licencia.sosLicencia(codigo))
		// return licencia;
		// }
		return licencia;
	}

	public void modificarLicencia(int codigo, String tipo, String motivo, float haberes, int tiempo,
			int antiguedadRequerida, boolean certificado) {
		/**
		 * TODO Había pensado un do while para que se ejecute y si alguno es
		 * nulo que vuelva tire el error, pero los int y los boolean no aceptan
		 * nulos. IDEAS?
		 **/
		Licencia lic = buscarLicencia(codigo);
		if (lic != null) {
			lic.setCodigo(codigo);
			lic.setTipo(tipo);
			lic.setMotivo(motivo);
			lic.setHaberes(haberes);
			lic.setTiempo(tiempo);
			lic.setAntiguedadRequerida(antiguedadRequerida);
			lic.setCertificado(certificado);
			LicenciaSRV.guardarLicencia(lic);
		} else {
			/** TODO El codigo no corresponde a una licencia existente **/
		}
	}

	// No tendría que ser una baja logica?? yo segui lo que decia las pantallas
	public void eliminarLicencia(int codigo, String tipo, String motivo) {
		Licencia lic = buscarLicencia(codigo);
		if (lic != null) {
			lic = null;
			LicenciaSRV.eliminarLicencia(lic);
		}
	}

	public void altaLicencia(int codigo, String tipo, String motivo, float haberes, int tiempo,
			int antiguedadRequerida, boolean certificado) {
		Licencia lic = buscarLicencia(codigo);
		if (lic == null) {
			lic = new Licencia(codigo, tipo, motivo, haberes, tiempo, antiguedadRequerida, certificado);
			LicenciaSRV.guardarLicencia(lic);
		} else {
			/** TODO La licencia YA existe **/
		}
	}

	// --------------------------ADMINISTRACION DE ESCUELAS----------------------//

	// Busco una sola escuela
	public Escuela buscarEscuela(int numero) {
		Escuela escuela = EscuelaSRV.buscarEscuela(numero);
		// for(Escuela escuela : escuelas){
		// if(escuela.sosEscuela(numero))
		// return escuela;
		// }
		return escuela;
	}

	// Para filtrar la busqueda por alguno de los campos
	public Vector<Escuela> buscarEscuelas(int numero, String nombre) {
		Vector<Escuela> escuelasEncontradas = EscuelaSRV.recuperarEscuelas(numero, nombre);
		// for(Escuela escuela : escuelas){
		// if(escuela.hayEscuelas(numero, nombre))
		// escuelasEncontradas.add(escuela);
		// }
		return escuelasEncontradas;
	}

	public void altaEscuela(int numero, String nombre, float cargoZona) {
		Escuela esc = buscarEscuela(numero);
		if (esc == null) {
			esc = new Escuela(numero, nombre, cargoZona);
			EscuelaSRV.guardarEscuela(esc);
		} else {
			/** TODO La ecuela YA existe **/
		}
	}

	public void modificarEscuela(int numero, String nombre, float cargoZona) {
		Escuela esc = buscarEscuela(numero);
		if (esc != null) {
			esc.setNro(numero);
			esc.setNombre(nombre);
			esc.setCargoZona(cargoZona);
			EscuelaSRV.guardarEscuela(esc);
		} else {
			/** TODO El numero no corresponde a una escuela existente **/
		}
	}

	// No tendria que ser una baja logica? yo segui lo que decian las pantallas
	public void eliminarEscuela(int numero) {
		Escuela esc = buscarEscuela(numero);
		if (esc != null) {
			esc.setInactiva();
			EscuelaSRV.guardarEscuela(esc);
		}
	}

	// --------------------------ADMINISTRACION DE CARGOS----------------------//

	// Para filtrar la busqueda por alguno de los campos
	public Vector<Cargo> buscarCargos(String nombreCargo, float sueldoBasico, int horasTrabajo) {
		Vector<Cargo> cargosAux = CargoSRV.recuperarCargos(nombreCargo, sueldoBasico, horasTrabajo);
		// for (Cargo cargo : cargos) {
		// if(cargo.getNombre().contains(nombreCargo) ||
		// cargo.getSueldoBasico()==sueldoBasico ||
		// cargo.getHorasTrabajo()==horasTrabajo){
		// cargos.add(cargo);
		// }
		// }
		return (Vector<Cargo>) cargos;
	}

	// Para buscar un solo Cargo
	public Cargo buscarCargo(int idCargo) {
		Cargo cargo = CargoSRV.buscarCargo(idCargo);
		return cargo;
	}

	public void modificarCargo(int idCargo, String nombre, float sueldoBasico, int horasTrabajo) {
//		Cargo cargo = buscarCargo(nombre);
//		if (cargo != null) {
//			cargo.setIdCargo(idCargo);
//			cargo.setNombre(nombre);
//			cargo.setSueldoBasico(sueldoBasico);
//			cargo.setHoras(horasTrabajo);
//			CargoSRV.guardarCargo(cargo);
//		} else {
//			/** TODO El codigo no corresponde a un cargo existente **/
//		}
	}

	public List<Cargo> recuperarCargos() {
		return CargoSRV.recuperarTodosLosCargos();
	}

	public void altaCargo(String nombreCargo, float sueldoBasico, int horasTrabajo) {
		Cargo cargo = buscarCargoPorNombre(nombreCargo);
		if (cargo == null) {
			cargo = new Cargo(nombreCargo, sueldoBasico, horasTrabajo, true);
			CargoSRV.guardarCargo(cargo);
		} else {
			/** TODO Ya existe un cargo con el id ingresado **/
		}
	}

	private Cargo buscarCargoPorNombre(String nombreCargo) {
		Cargo cargo = CargoSRV.buscarCargoPorNombre(nombreCargo);
		return cargo;
	}

	// Baja lógica
	public void elminarCargo(int idCargo) {
		Cargo cargo = buscarCargo(idCargo);
		if (cargo != null || cargo.isEstado() == true) {
			cargo.setEstado(false);
			CargoSRV.actualizarCargo(cargo);
		} else {
			/**
			 * TODO No existe ningun cargo con ese id o se encuentra inactivo
			 **/
		}
	}

	// --------------------------ADMINISTRACION DE CLASES----------------------//

	// Para filtrar la busqueda por alguno de los campos
	public Vector<Clase> buscarClases(int numero, String nombre, String curso, String division) {
		Vector<Clase> clases = ClaseSRV.recuperarClases(numero, nombre, curso, division);
		// for(Clase clase : clases){
		// if(clase.hayClases(numero, nombre, curso, division))
		// clases.add(clase);
		// }
		return clases;
	}

	// Para buscar una sola Clase
	public Clase buscarClase(int numero) {
		Clase clase = ClaseSRV.buscarClase(numero);
		// for(Clase clase : clases){
		// if(clase.sosClase(numero))
		// return clase;
		// }
		return clase;
	}

	public void modificarClase(int numero, String nombre, String curso, String division, float valor_hc,
			int hsSemanales) {
		Clase clase = buscarClase(numero);
		if (clase != null) {
			clase.setNroClase(numero);
			clase.setNombre(nombre);
			clase.setCurso(curso);
			clase.setDivision(division);
			clase.setHsSemanales(hsSemanales);
			clase.setValor_hc(valor_hc);
			ClaseSRV.guardarClase(clase);
		} else {
			/** TODO No existe ninguna clase con ese numero */
		}
	}

	// Baja lógica
	public void eliminarClase(int numero) {
		Clase clase = buscarClase(numero);
		if (clase != null || clase.isEstado() == true) {
			clase.setEstado(false);
			ClaseSRV.guardarClase(clase);
		} else {
			/** TODO La clase no existe o se encuentra inactiva */
		}
	}

	public void altaClase(int numero, String nombre, String curso, String division, float valor_hc, int hsSemanales) {
		Clase clase = buscarClase(numero);
		if (clase == null) {
			clase = new Clase(nombre, numero, curso, division, valor_hc, hsSemanales, true);
			ClaseSRV.guardarClase(clase);
		} else {
			/** TODO Ya existe una clase con el numero ingresado */
		}
	}

	// --------------------------ADMINISTRACION DE NOVEDADES----------------------//

	// Se concidera Licencia siempre y cuando sea justificable (Ver tabla de
	// licencias)
	
	
	public void cargarInasistenciaDocente(String dni, Date fecha, Licencia licencia,
			float horasCatedraAusente, int cantDiasAusente, Clase clase){
		int semanasMesCorriente = cantidadSemandasMes(fecha.getMonth());
		Empleado empleado = buscarEmpleado(dni);
		if (empleado != null) {
			LicenciaEmpleado licenciaDocente = empleado.getLicencia(licencia.getCodigo());
			LicenciaEmpleado licenciaDocenteSinJustificar = empleado.getLicencia(3);
			Novedad novedadPorLicencia=null;
			Novedad novedadPorInasistenciaSinJustificar=null;
			if(licenciaDocente!=null){
				int diasRestantes = licenciaDocente.getCantDisponible();
				if(diasRestantes-cantDiasAusente<0){
					Licencia licenciaSinJustificar = LicenciaSRV.buscarLicencia(3);
					novedadPorInasistenciaSinJustificar = new Novedad(fecha,licenciaSinJustificar,horasCatedraAusente,semanasMesCorriente,cantDiasAusente - diasRestantes);
					novedadPorLicencia = new Novedad(fecha,licenciaSinJustificar,horasCatedraAusente,semanasMesCorriente,diasRestantes);
					if(licenciaDocenteSinJustificar==null){
						licenciaDocenteSinJustificar = new LicenciaEmpleado(licenciaSinJustificar.getTiempo(),licenciaSinJustificar);
						empleado.agregarLicenciaEmpleado(licenciaDocenteSinJustificar);
					}
					licenciaDocente.setCantDisponible(0);
					licenciaDocenteSinJustificar.setCantDisponible(licenciaDocenteSinJustificar.getCantDisponible()-(cantDiasAusente - diasRestantes));
					
				}else{
					novedadPorLicencia = new Novedad(fecha,licencia,horasCatedraAusente,semanasMesCorriente,cantDiasAusente);
					licenciaDocente.setCantDisponible(diasRestantes - cantDiasAusente);
				}
			}else{
				if(empleado.getAntiguedad()>licencia.getAntiguedadRequerida()){
					licenciaDocente = new LicenciaEmpleado(licencia.getTiempo(), licencia);
					empleado.agregarLicenciaEmpleado(licenciaDocente);
					if(cantDiasAusente>licenciaDocente.getCantDisponible()){
						Licencia licenciaSinJustificar = LicenciaSRV.buscarLicencia(3);
						novedadPorInasistenciaSinJustificar = new Novedad(fecha,licenciaSinJustificar,horasCatedraAusente,semanasMesCorriente,cantDiasAusente - licenciaDocente.getCantDisponible());
						novedadPorLicencia = new Novedad(fecha,licencia,horasCatedraAusente,semanasMesCorriente,licenciaDocente.getCantDisponible());
						if(licenciaDocenteSinJustificar==null){
							licenciaDocenteSinJustificar = new LicenciaEmpleado(licenciaSinJustificar.getTiempo(),licenciaSinJustificar);
							empleado.agregarLicenciaEmpleado(licenciaDocenteSinJustificar);
						}
						licenciaDocente.setCantDisponible(0);
						licenciaDocenteSinJustificar.setCantDisponible(licenciaDocenteSinJustificar.getCantDisponible()-(cantDiasAusente - licenciaDocente.getCantDisponible()));
					}else{
						novedadPorLicencia = new Novedad(fecha,licencia,horasCatedraAusente,semanasMesCorriente,cantDiasAusente);
						licenciaDocente.setCantDisponible(licenciaDocente.getCantDisponible() - cantDiasAusente);
					}
				}else{
					Licencia licenciaSinJustificar = LicenciaSRV.buscarLicencia(3);
					if(licenciaDocenteSinJustificar==null){
						licenciaDocenteSinJustificar = new LicenciaEmpleado(licenciaSinJustificar.getTiempo(),licenciaSinJustificar);
						empleado.agregarLicenciaEmpleado(licenciaDocenteSinJustificar);
					}
					novedadPorInasistenciaSinJustificar = new Novedad(fecha,licenciaSinJustificar,horasCatedraAusente,semanasMesCorriente,cantDiasAusente);
					
				}
			}
			if(novedadPorLicencia!=null){
				novedadPorLicencia.setClase(clase);
				empleado.agregarNovedad(novedadPorLicencia);
			}
			if(novedadPorInasistenciaSinJustificar!=null){
				novedadPorInasistenciaSinJustificar.setClase(clase);
				empleado.agregarNovedad(novedadPorInasistenciaSinJustificar);
			}
			EmpleadoSRV.actualizarEmpleado(empleado);
		}
		
	}
	
	public void cargarInasistenciaEmpleado(String dni, Date fecha, Licencia licencia,
			float horasCatedraAusente, int cantDiasAusente){
		int semanasMesCorriente = cantidadSemandasMes(fecha.getMonth());
		Empleado empleado = buscarEmpleado(dni);
		if (empleado != null) {
			LicenciaEmpleado licenciaEmpleado = empleado.getLicencia(licencia.getCodigo());
			LicenciaEmpleado licenciaDocenteSinJustificar = empleado.getLicencia(3);
			Novedad novedadPorLicencia=null;
			Novedad novedadPorInasistenciaSinJustificar=null;
			if(licenciaEmpleado!=null){
				int diasRestantes = licenciaEmpleado.getCantDisponible();
				if(diasRestantes-cantDiasAusente<0){
					Licencia licenciaSinJustificar = LicenciaSRV.buscarLicencia(3);
					novedadPorInasistenciaSinJustificar = new Novedad(fecha,licenciaSinJustificar,horasCatedraAusente,semanasMesCorriente,cantDiasAusente - diasRestantes);
					novedadPorLicencia = new Novedad(fecha,licenciaSinJustificar,horasCatedraAusente,semanasMesCorriente,diasRestantes);
					if(licenciaDocenteSinJustificar==null){
						licenciaDocenteSinJustificar = new LicenciaEmpleado(licenciaSinJustificar.getTiempo(),licenciaSinJustificar);
						empleado.agregarLicenciaEmpleado(licenciaDocenteSinJustificar);
					}
					licenciaEmpleado.setCantDisponible(0);
					licenciaDocenteSinJustificar.setCantDisponible(licenciaDocenteSinJustificar.getCantDisponible()-(cantDiasAusente - diasRestantes));
					
				}else{
					novedadPorLicencia = new Novedad(fecha,licencia,horasCatedraAusente,semanasMesCorriente,cantDiasAusente);
					licenciaEmpleado.setCantDisponible(diasRestantes - cantDiasAusente);
				}
			}else{
				if(empleado.getAntiguedad()>licencia.getAntiguedadRequerida()){
					licenciaEmpleado = new LicenciaEmpleado(licencia.getTiempo(), licencia);
					empleado.agregarLicenciaEmpleado(licenciaEmpleado);
					if(cantDiasAusente>licenciaEmpleado.getCantDisponible()){
						Licencia licenciaSinJustificar = LicenciaSRV.buscarLicencia(3);
						novedadPorInasistenciaSinJustificar = new Novedad(fecha,licenciaSinJustificar,horasCatedraAusente,semanasMesCorriente,cantDiasAusente - licenciaEmpleado.getCantDisponible());
						novedadPorLicencia = new Novedad(fecha,licencia,horasCatedraAusente,semanasMesCorriente,licenciaEmpleado.getCantDisponible());
						if(licenciaDocenteSinJustificar==null){
							licenciaDocenteSinJustificar = new LicenciaEmpleado(licenciaSinJustificar.getTiempo(),licenciaSinJustificar);
							empleado.agregarLicenciaEmpleado(licenciaDocenteSinJustificar);
						}
						licenciaEmpleado.setCantDisponible(0);
						licenciaDocenteSinJustificar.setCantDisponible(licenciaDocenteSinJustificar.getCantDisponible()-(cantDiasAusente - licenciaEmpleado.getCantDisponible()));
					}else{
						novedadPorLicencia = new Novedad(fecha,licencia,horasCatedraAusente,semanasMesCorriente,cantDiasAusente);
						licenciaEmpleado.setCantDisponible(licenciaEmpleado.getCantDisponible() - cantDiasAusente);
					}
				}else{
					Licencia licenciaSinJustificar = LicenciaSRV.buscarLicencia(3);
					if(licenciaDocenteSinJustificar==null){
						licenciaDocenteSinJustificar = new LicenciaEmpleado(licenciaSinJustificar.getTiempo(),licenciaSinJustificar);
						empleado.agregarLicenciaEmpleado(licenciaDocenteSinJustificar);
					}
					novedadPorInasistenciaSinJustificar = new Novedad(fecha,licenciaSinJustificar,horasCatedraAusente,semanasMesCorriente,cantDiasAusente);
					
				}
			}
			if(novedadPorLicencia!=null){
				empleado.agregarNovedad(novedadPorLicencia);
			}
			if(novedadPorInasistenciaSinJustificar!=null){
				empleado.agregarNovedad(novedadPorInasistenciaSinJustificar);
			}
			EmpleadoSRV.actualizarEmpleado(empleado);
		}
		
	}
	
	

	public Vector<Novedad> buscarNovedad(String tipo, String motivo, Date fechaInicio, Date fechaHasta, String dni) {
		Vector<Novedad> novedadesAux = new Vector<Novedad>();
		Empleado empleado = buscarEmpleado(dni);
		if (empleado != null) {
			novedadesAux = (Vector<Novedad>) empleado.getNovedades();
		} else {
			novedadesAux = NovedadSRV.recuperarNovedades(tipo, motivo, fechaInicio, fechaHasta);
		}
		return novedadesAux;
	}


	public List<Clase> getClases() {
		return ClaseSRV.cargarClases();
	}

	public List<Empleado> buscarEmpleados(String nombre, String apellido, String cuil, String dni, boolean estado,
			Escuela escuela) {
		return EmpleadoSRV.buscarEmpleados(apellido, nombre, cuil, dni, escuela.getNro(), estado);
	}

	public List<Escuela> recuperarEscuelas() {
		return EscuelaSRV.recuperarEscuelas();
	}

	public List<Empleado> recuperarEmpleados() {
		return EmpleadoSRV.recuperarEmpleados();
	}

	public int cantidadSemandasMes(int mes) {
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, mes);
		int cuenta = 0;
		while (cal.get(Calendar.MONTH) == mes) {
			if (cal.get(Calendar.DAY_OF_WEEK) == 1)
				cuenta++;
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return cuenta;
	}

	public List<Licencia> recuperarLicencias() {
		return LicenciaSRV.recuperarLicencias();
	}

	public List<ConCargo> recuperarEmpleadosConCargo() {
		return EmpleadoSRV.recuperarEmpleadosConCargo();
	}

	public List<SinCargo> recuperarProfesores() {
		return EmpleadoSRV.recuperarProfesores();
	}

	public float calcularDescuentoVista(String dni) {
		SinCargo empleadoS = EmpleadoSRV.buscarEmpleadoSinCargo(dni);
		ConCargo empleadoC = EmpleadoSRV.buscarEmpleadoConCargo(dni);
		float descuentoTotal = 0;
		int mes = (new Date().getMonth()) + 1;
		if (empleadoS != null) {
			descuentoTotal = calcularDescuento(dni, mes,0);
			return descuentoTotal;
		}
		if (empleadoC != null) {
			for (Cargo cargo : empleadoC.getCargos()) {
				descuentoTotal += calcularDescuento(dni,mes,cargo.getSueldoBasico());
			}
			return descuentoTotal;
		}
		return 0;
	}

}
