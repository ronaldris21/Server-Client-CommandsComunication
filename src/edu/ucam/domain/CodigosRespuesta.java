package edu.ucam.domain;
/**
 * <p> 
 * Es una enumeración para identificar 
 * con palabras los códigos de respuesta del servidor
 * </p>
 */
public enum CodigosRespuesta {
	///2xx Correctos
	//4xx fallado
	OK(200),
	CREATED(201),
	BADREQUEST(400),
	NOTFOUND(404),
	INTERNAL_SERVER_ERROR(500)
	
	;
	
	
	private int codigo;
	/**
     * Obtiene el codigo
     */
	public int getCode()
	{
		return this.codigo;
	}
	private CodigosRespuesta(int codigo)
	{
		this.codigo = codigo;
	}
}
