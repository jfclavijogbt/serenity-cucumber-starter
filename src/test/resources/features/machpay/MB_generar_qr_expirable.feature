# language: es

@MachPayQR_TE
@issue:MACHEX-180
#noinspection SpellCheckingInspection
Característica: Generar QRs de cobro en Mach Business
  Yo como analista de negocio de Mach Business
  quiero generar QRs de cobro
  para que los usuarios puedan pagar con MACH

  Antecedentes:
    Dado que el usuario navegó a la página de inicio de sesión de MACH Pay
    Y ha ingresado credenciales de acceso válidas

  @ImmediateChargeQRGeneration @issue:MACHEX-182
  Esquema del escenario: Escenario: Generar QR de cobro inmediato
    Cuando ingresa a la opción de Cobro inmediato
    Y genera un QR con los datos de cobro:
      | Monto   | Propina   | Descripción   | Nombre   | Sucursal   | Caja   |
      | <Monto> | <Propina> | <Descripción> | <Nombre> | <Sucursal> | <Caja> |
    Entonces se generará un QR con los datos de cobro para "<Descripción>"
    Ejemplos: [QR de cobro inmediato]
      | Monto | Propina     | Descripción | Nombre | Sucursal                         | Caja                    |
      | 1000  | 10%         | QR con 10%  | Juan   | Sucursal Automatizada Las Condes | Caja 10                 |
      | 1500  | Sin propina | QR neto     | María  | Sucursal Automatizada Las Condes | Terminal Automatizado 7 |
      | 2000  | 15%         | QR con 15%  | David  | Sucursal Automatizada Las Condes | Caja 1                  |
