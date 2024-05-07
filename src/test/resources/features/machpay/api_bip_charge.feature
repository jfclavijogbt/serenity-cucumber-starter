# language: es

@ApiBipTE
@issue:MQC-129

#noinspection SpellCheckingInspection
Característica: Verificar la salud de los servicios de recarga Bip!
  Yo como analista de negocio
  Quiero verificar la salud de los servicios de recarga Bip!
  Para asegurarme de que los usuarios puedan recargar sus tarjetas


  @verifyBipCharge
  @issue:MQC-130
  Escenario: Verificar tarjeta Bip! cargada
    Dado que accedí al API de Recarga Bip!
    Cuando consulte la tarjeta número 1234567 en el servicio de validación de tarjetas
    Entonces La tarjeta número 1234567 aparecerá validada

  @issue:MQC-131
  @verifyBipBalance
  Escenario: Verificar saldo de tarjeta Bip!
    Dado que accedí al API de Recarga Bip!
    Cuando consulte el saldo de la tarjeta número 1234567 en el servicio de validación de tarjetas
    Entonces El saldo de la tarjeta aparecerá con saldo

  @issue:MQC-132
  @makeBipCharge
  Escenario: Realizar recarga de tarjeta Bip!
    Dado que accedí al API de Recarga Bip!
    Cuando realice una recarga de 1000 pesos a la tarjeta número 1234567
    Entonces La recarga de 1000 pesos a la tarjeta número 1234567 aparecerá realizada
    Y el estado de la transacción será "PENDING_PAYMENT"