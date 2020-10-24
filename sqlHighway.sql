CREATE DATABASE highway;
USE highway;

Create table t_plegarias (
	IDP Int NOT NULL AUTO_INCREMENT,
	Titulo Text NOT NULL,
	Cuerpo Text NOT NULL,
	ComIncumplidos Int NOT NULL,
	Activo Bool NOT NULL,
	UNIQUE (IDP),
 Primary Key (IDP)) ENGINE = InnoDB;

Create table t_creyentes (
	IDC Int NOT NULL AUTO_INCREMENT,
	Nombre Text NOT NULL,
	Email Text NOT NULL,
	Edad Int NOT NULL,
	Activo Bool NOT NULL,
	UNIQUE (IDC),
 Primary Key (IDC)) ENGINE = InnoDB;

Create table t_comentarios (
	IDCo Int NOT NULL AUTO_INCREMENT,
	IDEscritor Int NOT NULL,
	IDPlegaria Int NOT NULL,
	Comentario Text NOT NULL,
	Cumplido Bool NOT NULL,
	UNIQUE (IDCo),
 Primary Key (IDCo,IDEscritor,IDPlegaria)) ENGINE = InnoDB;

Alter table t_plegarias add unique Titulo (Titulo(255));
Alter table t_creyentes add unique Email (Email(255));

Alter table t_comentarios add Foreign Key (IDEscritor) references t_creyentes (IDC) on delete  restrict on update cascade;

Alter table t_comentarios add Foreign Key (IDPlegaria) references t_plegarias(IDP) on delete  restrict on update cascade;