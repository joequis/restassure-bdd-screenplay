Feature: Gestión de usuarios
  Como QA
  Quiero crear y consultar usuarios
  Para validar la API de ReqRes

  @crear-usuario
  Scenario: Crear usuario exitosamente
    Given un actor configurado para llamar la API
    When crea un usuario con nombre "Joel" y trabajo "QA"
    Then el código de respuesta debe ser 201
    And la respuesta contiene el nombre "Joel" y trabajo "QA"

  @Obtener-usuario
  Scenario: Obtener usuario por ID
    Given un actor configurado para llamar la API
    When consulta el usuario con id 2
    Then el código de respuesta debe ser 200
