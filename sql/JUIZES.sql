CREATE OR REPLACE
PROCEDURE verificar_matriculacao(
  v_competidor IN NUMBER,
  v_bateria IN NUMBER
)
IS
  v_dummy NUMBER;
  nao_matriculado EXCEPTION;
BEGIN
  -- vai jogar excepção se não tem matriculação ou a matriculação já tem
  -- resultado (desclassificação ou um tempo)
  SELECT NULL INTO v_dummy
  FROM Participacoes
  WHERE Bateria = v_bateria
    AND Competidor = v_competidor
    AND Desclassificado IS NULL
    AND Tempo IS NULL;
EXCEPTION
  WHEN no_data_found THEN
    RAISE nao_matriculado;
END verificar_matriculacao;


CREATE OR REPLACE
PROCEDURE inserir_Resultado(
  v_competidor IN NUMBER,
  v_bateria IN NUMBER,
  v_tempo IN NUMBER
)
IS
BEGIN
  verificar_matriculacao(v_competidor, v_bateria);
  
  UPDATE Participacoes
  SET Desclassificado = 0, Tempo = v_tempo
  WHERE Bateria = v_bateria
    AND Competidor = v_competidor;
END inserir_Resultado;


CREATE OR REPLACE
PROCEDURE inserir_Desclassificacao(
  v_competidor IN NUMBER,
  v_bateria IN NUMBER
)
IS
BEGIN
  verificar_matriculacao(v_competidor, v_bateria);
  
  UPDATE Participacoes
  SET Desclassificado = 1
  WHERE Bateria = v_bateria
    AND Competidor = v_competidor;
END inserir_Desclassificacao;


CREATE OR REPLACE
PROCEDURE fechar_Bateria(
  v_bateria IN NUMBER
)
IS
  v_bateria_info Bateria_Info%ROWTYPE;
  CURSOR c_bateria IS SELECT *
                      FROM Bateria_Info
                      WHERE Id = v_bateria;
  v_competidor NUMBER;
  CURSOR c_melhores IS SELECT Competidor
                       FROM Participacoes
                       WHERE Bateria = v_bateria
                        AND Tempo IS NOT NULL
                        AND ROWNUM <= 2
                       ORDER BY Tempo ASC;
  resultados_desaparecidas EXCEPTION;
  ja_fechado EXCEPTION;
BEGIN
  OPEN c_bateria;
  FETCH c_bateria INTO v_bateria_info;
  CLOSE c_bateria;
  
  IF v_bateria_info.Tipo > 1 THEN
    IF v_bateria_info.Fechado = NULL THEN
      RAISE ja_fechado;
    ELSE IF v_bateria_info.Participantes > v_bateria_info.Resultados THEN
        RAISE resultados_desaparecidas;
      ELSE
        OPEN c_melhores;
        LOOP
          FETCH c_melhores INTO v_competidor;
          EXIT WHEN c_melhores%NOTFOUND;
          
         registrar_por_Prova(v_competidor, v_bateria_info.Tipo - 1, v_bateria_info.Categoria);
        END LOOP;
        CLOSE c_melhores;
        
        UPDATE Bateria SET Fechado = 1 WHERE Id = v_bateria;
      END IF;
    END IF;
  END IF;
END fechar_Bateria;