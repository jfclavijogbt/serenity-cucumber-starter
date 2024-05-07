# language: es

@MachPayLoginTE
#noinspection SpellCheckingInspection
@issue:MQC-129
Característica: Inicio de sesión en Mach Business
  Yo como analista de negocio de Mach Business
  requiero iniciar sesión en la web de Mach Business
  para generar QRs de cobro.

  Antecedentes:
    Dado que el usuario navegó a la página de inicio de sesión de MACH Pay

  @issue:MQC-128
  @SuccessfullyLoginMACHPay
  Escenario: Iniciar sesión exitosamente en MACHPay
    Cuando ingresa credenciales de acceso válidas
    Entonces podrá ver la página de inicio

  @issue:MQC-127
  @FailedLoginMACHPay
  Escenario: Iniciar sesión fallida en MACHPay
    Cuando ingresa credenciales de acceso inválidas
    Entonces verá el mensaje de error: "El correo y/o contraseña no es correcto"
    Y no podrá acceder a la página de inicio