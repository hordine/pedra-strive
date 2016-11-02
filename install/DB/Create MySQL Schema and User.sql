delimiter $$

create schema pedrastrive DEFAULT CHARACTER SET utf8 COLLATE utf8_bin $$

CREATE USER pedraStriveUser@localhost
IDENTIFIED BY 'pedraStrivePWD' $$

Grant ALL on pedraStrive.* to pedraStriveUser@localhost $$

/*
Set these properties to the options file: 
	character-set-server = utf8
	skip-character-set-client-handshake = checked	
*/

delimiter ;
