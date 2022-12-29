package edu.ucam.domain;

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
	public int getCode()
	{
		return this.codigo;
	}
	private CodigosRespuesta(int codigo)
	{
		this.codigo = codigo;
	}
}
