package controlador;

import java.util.Collection;
import java.util.Date;
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

	// --------------------------ADMINISTRACION DE
	// EMPLEADOS----------------------//
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
		SinCargo empleado = (SinCargo) EmpleadoSRV.buscarEmpleado(dni);
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
		SinCargo empleado = (SinCargo) buscarEmpleado(dni);
		if (!empleado.tenesClase(clase.getDivision(), clase.getCurso())) {
			empleado.getClases().add(clase);
			empleado.setEstado(true);
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tr = session.beginTransaction();
			session.update(empleado);
			tr.commit();
			session.close();

		} else {
			/** TODO El empleado ya tiene esa clase asignada **/
		}
		// for(SinCargo emp : sinCargos){
		// if(emp.getDni().equals(dni)){
		// if(!emp.tenesClase(clase.getDivision(), clase.getCurso())){
		// emp.getClases().add(clase);
		// if(emp.isEstado() == false)
		// emp.setEstado(true);
		// }else{
		// }
		// }
		//
		// }
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
		// for(ConCargo emp : conCargos){
		// boolean flag = false;
		// if(emp.getDni().equals(dni)){
		// for(Cargo cargoAux : emp.getCargos()){
		// if(cargoAux.getIdCargo()==cargo.getIdCargo()){
		// flag = true;
		// }
		// }
		// }
		// if(flag == true){
		// emp.getCargos().add(cargo);
		// if(emp.isEstado() == false)
		// emp.setEstado(true);
		// }
		//
		// }
	}

	public void altaEmpleado(String nombre, String apellido, String dni, String cuil, Date fechaDeNacimiento,
			int antiguedad, int nroEscuela) {
		Empleado empleado = buscarEmpleado(dni);
		if (empleado == null) {
			empleado = new ConCargo();
			/**
			 * TODO No estoy seguro de que este dando bien el alta de un
			 * empleado
			 **/

			empleado.setNombre(nombre);
			empleado.setApellido(apellido);
			empleado.setDni(dni);
			empleado.setCuil(cuil);
			empleado.setFechaDeNacimiento(fechaDeNacimiento);
			empleado.setAntiguedad(antiguedad);
			Date fechaHoy = new Date();
			empleado.setFechaDeIngreso(fechaHoy);
			/**
			 * TODO ver de agregar el nombre correspondiente al numero de
			 * escuela
			 **/
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
			EmpleadoSRV.grabarEmpleado(empleado);
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
			for (LicenciaEmpleado licencia : emp.getLicenciasEmpleados()) {
				mostrarDatos.getLicencias().add(licencia);
			}
		}
		return mostrarDatos;
	}

	public float calcularSueldo(String dni, int mes) {
		SinCargo empleadoS = EmpleadoSRV.buscarEmpleadoSinCargo(dni);
		ConCargo empleadoC = EmpleadoSRV.buscarEmpleadoConCargo(dni);
		float sueldoTotal = 0;
		if (empleadoS != null) {
			SueldoV detalleSueldo = empleadoS.vistaSueldoEmpleado(
					mes); /**
							 * TODO Esto esta bien? Se hace acá el para mostrar?
							 **/
			sueldoTotal = empleadoS.calcularSueldo();
			return sueldoTotal;
		}
		if (empleadoC != null) {
			SueldoV detalleSueldo = empleadoC.vistaSueldoEmpleado(
					mes); /**
							 * TODO Esto esta bien? Se hace acá el para mostrar?
							 **/
			sueldoTotal = empleadoC.calcularSueldo();
			return sueldoTotal;
		}
		return 0; /** TODO mensaje error **/
	}

	public float calcularDescuento(String dni, Date fechaActual) {
		Empleado emp = buscarEmpleado(dni);
		float descuento = 0;
		if (emp != null) {
			for (Novedad novedad : emp.getNovedades()) {
				if (novedad.getFecha().getMonth() == fechaActual.getMonth()) // como
																				// carajo
																				// hago
																				// esto!?
					descuento += novedad.obtenerDescuentoTotal();
			}
			// DescuentoV detalleDescuento = emp.vistaDescuento(mes);
			return descuento;
		}
		return 0; /** TODO mensaje error **/
	}

	public Vector<EmpleadoV> mostrarTodosLosEmpleados() {
		Vector<EmpleadoV> mostrarDatos = new Vector<EmpleadoV>();
		;
		EmpleadoV datos = null;
		for (Empleado emp : empleados) {
			datos.setApellido(emp.getApellido());
			datos.setNombre(emp.getNombre());
			datos.setDni(emp.getDni());
			datos.setCuil(emp.getCuil());
			datos.setNroEscuela(emp.getEscuela().getNro());
			datos.setNombreEscuela(emp.getEscuela().getNombre());

			mostrarDatos.add(datos);
		}

		return mostrarDatos;
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
	public Licencia buscarLicencia(String codigo) {
		Licencia licencia = LicenciaSRV.buscarLicencia(codigo);
		// for(Licencia licencia : licencias){
		// if(licencia.sosLicencia(codigo))
		// return licencia;
		// }
		return licencia;
	}

	public void modificarLicencia(String codigo, String tipo, String motivo, float haberes, int tiempo,
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
	public void eliminarLicencia(String codigo, String tipo, String motivo) {
		Licencia lic = buscarLicencia(codigo);
		if (lic != null) {
			lic = null;
			LicenciaSRV.eliminarLicencia(lic);
		}
	}

	public void altaLicencia(String codigo, String tipo, String motivo, float haberes, int tiempo,
			int antiguedadRequerida, boolean certificado) {
		Licencia lic = buscarLicencia(codigo);
		if (lic == null) {
			lic = new Licencia(codigo, tipo, motivo, haberes, tiempo, antiguedadRequerida, certificado);
			LicenciaSRV.guardarLicencia(lic);
		} else {
			/** TODO La licencia YA existe **/
		}
	}

	// --------------------------ADMINISTRACION DE
	// ESCUELAS----------------------//

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

	// --------------------------ADMINISTRACION DE
	// CARGOS----------------------//

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
	public Cargo buscarCargo(String nombreCargo) {
		Cargo cargo = CargoSRV.buscarCargo(nombreCargo);
		// for(Cargo cargo : cargos){
		// if(cargo.sosCargo(nombreCargo))
		// return cargo;
		// }
		return cargo;
	}

	public void modificarCargo(int idCargo, String nombre, float sueldoBasico, int horasTrabajo) {
		Cargo cargo = buscarCargo(nombre);
		if (cargo != null) {
			cargo.setIdCargo(idCargo);
			cargo.setNombre(nombre);
			cargo.setSueldoBasico(sueldoBasico);
			cargo.setHoras(horasTrabajo);
			CargoSRV.guardarCargo(cargo);
		} else {
			/** TODO El codigo no corresponde a un cargo existente **/
		}
	}

	public List<Cargo> recuperarCargos() {
		return CargoSRV.recuperarTodosLosCargos();
	}

	public void altaCargo(String nombreCargo, float sueldoBasico, int horasTrabajo) {
		Cargo cargo = buscarCargo(nombreCargo);
		if (cargo == null) {
			cargo = new Cargo(nombreCargo, sueldoBasico, horasTrabajo, true);
			CargoSRV.guardarCargo(cargo);
		} else {
			/** TODO Ya existe un cargo con el id ingresado **/
		}
	}

	// Baja lógica
	public void elminarCargo(String nombreCargo) {
		Cargo cargo = buscarCargo(nombreCargo);
		if (cargo != null || cargo.isEstado() == true) {
			cargo.setEstado(false);
			CargoSRV.guardarCargo(cargo);
		} else {
			/**
			 * TODO No existe ningun cargo con ese id o se encuentra inactivo
			 **/
		}
	}

	// --------------------------ADMINISTRACION DE
	// CLASES----------------------//

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

	// --------------------------ADMINISTRACION DE
	// NOVEDADES----------------------//

	// Se concidera Licencia siempre y cuando sea justificable (Ver tabla de
	// licencias)
	public void cargarLicenciaEmpleado(String dni, String codigo, String tipo, String motivo, float haberes,
			int antiguedadRequerida, boolean certificado, int semanasMesCorriente, float oblig_hc_mes,
			float oblig_hc_noTrab, int diasAusente) {
		Empleado emp = buscarEmpleado(dni);
		/**
		 * TODO Hay que guardar las modificaciones que se hacan en este metodo
		 * en la BD
		 **/
		if (emp != null) {
			Date fecha = new Date();
			for (Licencia licencia : licencias) {
				// Busco entre las licencias existentes
				if (codigo.equals(licencia.getCodigo())) {
					for (LicenciaEmpleado licEmp : licenciasEmpleado) {
						// Me fijo si el empleado ya tiene registro de esa
						// licencia, si tiene chequeo si le quedan dias
						// disponibles
						if (codigo.equals(licEmp.getLicencia().getCodigo())) {
							// chequeo si le quedan dias disponibles
							if (licEmp.getCantDisponible() >= diasAusente) {
								// TODO Aca no se actualiza la novedad, se crea
								// una nueva y se le agrega la licencia
								for (Novedad novedad : emp.getNovedades()) {
									if (codigo.equals(novedad.getLicencia().getCodigo())) {
										novedad.setFecha(fecha);
										novedad.setLicencia(licencia);
										// Seteo la clase?? Yo la volaría de
										// Novedad
									}
								}
								// Le descuento los dias que se esta tomando
								licEmp.setCantDisponible(licEmp.getCantDisponible() - diasAusente);
								// Si tiene certificado se marca como true
								if (certificado == true)
									licEmp.getLicencia().setCertificado(certificado);
							} else {
								/**
								 * TODO El empleado no tiene la antiguedad
								 * minima requerida para la licencia solicitada,
								 * ingresar como inasistencia
								 */
								// Si no le alcanzan los días disponibles se
								// incrementa el valor de cantClasesAusente de
								// Novedad en lo pasado por
								// parámetro menos los días que tenga
								// disponibles y se agregan los demas a la
								// licencia
								// TODO: Se obtiene la diferencia entre los dias
								// que falta y los de la licencia y eso es una
								// nueva novedad como INASISTENCIA, ademas de la
								// otra novedad creada por la licencia hasta
								// quedar en cero
								// Pongo en cero los días que le quedan porque
								// el tiempo que quiere agregar supera a los que
								// tiene
								int diasAusenteFueraLicencia = diasAusente - licEmp.getCantDisponible();
								licEmp.setCantDisponible(0);
								// Si tiene certificado se marca como true
								if (certificado == true)
									licEmp.getLicencia().setCertificado(certificado);
								for (Novedad novedad : emp.getNovedades()) {
									if (codigo.equals(novedad.getLicencia().getCodigo())) {
										novedad.setFecha(fecha);
										novedad.setLicencia(licencia);
										// Incremento cantClasesAusente en los
										// días que no pudo cubrir la licencia
										novedad.setCantClasesAusente(
												novedad.getCantClasesAusente() + (diasAusenteFueraLicencia));
									}
								}

							}
							// Si no tiene esa licencia, chequeo la antiguedad y
							// si puede se agrega -> Creo una nueva Novedad
						} else if (emp.getAntiguedad() >= licencia.getAntiguedadRequerida()) {
							// Si tiene certificado se marca como true
							if (certificado == true)
								licencia.setCertificado(true);
							LicenciaEmpleado nueva = new LicenciaEmpleado(licencia.getTiempo(), licencia);
							emp.getLicenciasEmpleados().add(nueva);
							Novedad novedad = new Novedad(fecha, licencia, oblig_hc_mes, semanasMesCorriente,
									diasAusente);
							emp.getNovedades().add(novedad);
						} else {
							/**
							 * TODO El empleado no tiene la antiguedad minima
							 * requerida para la licencia solicitada, ingresar
							 * como inasistencia
							 */
							// Si no tiene la antiguedad mínima requerida se
							// incrementa el valor de cantClasesAusente de
							// Novedad en lo pasado por parámetro
							// -> Creo una nueva Novedad
							Novedad novedad = new Novedad(fecha, licencia, oblig_hc_mes, semanasMesCorriente,
									diasAusente);
							emp.getNovedades().add(novedad);
						}
					}
				} else {
					/**
					 * TODO El codigo ingresado no pertenece a una licencia
					 * existente
					 */
				}
			}
		} else {
			/** TODO El dni ingresado no corresponde a un empleado existente */
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
		// for (Empleado empleado : empleados) {
		// if(empleado.sosEmpleado(dni)){
		// for (Novedad novedad : empleado.getNovedades()){
		// novedadesAux.add(novedad);
		// return novedadesAux;
		// }
		// }else{
		// for (Novedad novedad : empleado.getNovedades()) {
		// if (novedad.sosUnaNovedad(tipo, motivo, fechaInicio, fechaHasta)){
		// novedadesAux.add(novedad);
		// }
		// }
		// }
		// }
		// return novedadesAux;
	}

	// Se considera Inasistencia cuando no es justificable y hay que efectuar el
	// descuento correspondiente (el descuento se efectúa en calcularSueldo() )
	public void cargarInasistenciaEmpleado(String dni, Date fecha, Licencia licencia, int semanasMesCorriente,
			float oblig_hc_mes, int cantClasesAusente) {
		/**
		 * TODO Se debe crear una nueva novedad y cargar el o los días faltados
		 */
		Empleado emp = buscarEmpleado(dni);
		if (emp != null) {
			Novedad novedad = new Novedad(fecha, licencia, oblig_hc_mes, semanasMesCorriente, cantClasesAusente);
			emp.getNovedades().add(novedad);
			EmpleadoSRV.actualizarEmpleado(emp);
		} else {
			/** TODO El dni ingresado no corresponde a un empleado existente */
		}
	}

	public List<SinCargo> getProfesores() {
		// TODO Auto-generated method stub
		return (List<SinCargo>) this.sinCargos;
	}

	public List<Clase> getClases() {
		// TODO Auto-generated method stub
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

}
