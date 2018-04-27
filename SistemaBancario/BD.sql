SELECT * FROM sistemabancario.cuenta;

#update cuenta set favorita = 0 where Cliente_idCliente = 1;
#update cuenta set favorita = 1 where numeroCuenta = 987654321;7

select * from movimiento;

select * from Moneda;

#insert into Moneda(nombreMone, simbolo) values("Dolares","$")

select now()

select numeroCuenta,tipoMoneda,saldo from Cuenta where numeroCuenta ='123456789'

# idCuenta, numeroCuenta, saldo, tipoMoneda, Cliente_idCliente, favorita
'1', '5555', '10009.7', 'Dolares', '0', '0'
'2', '999', '9999', 'Colones', '0', '0'
'3', '7777', '7000', 'Colones', '0', '0'
'4', '753159', '20000', 'Dolares', '0', '0'
'5', '777', '7777', 'Colones', '0', '0'
'6', '555', '55555', 'Colones', '0', '0'
'7', '8888888', '900000', 'Colones', '0', '0'
'8', '123456789', '27940', 'Colones', '1', '1'
'9', '987654321', '20', 'Dolares', '1', '0'

select * from Cliente
DELETE FROM `sistemabancario`.`movimiento`
WHERE 1=1;


1


