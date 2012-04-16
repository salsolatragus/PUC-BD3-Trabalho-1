CREATE SEQUENCE Categoria_Indice;


CREATE SEQUENCE Bateria_Indice;


CREATE OR REPLACE
PROCEDURE inserir_Bateria (
  v_categoria IN NUMBER,
  v_tipo IN NUMBER,
  v_numero IN NUMBER
)
IS
BEGIN
  INSERT INTO Bateria (Id, Categoria, Tipo, Numero) VALUES
    (Bateria_Indice.NEXTVAL, v_categoria, v_tipo, v_numero);
END inserir_Bateria;


CREATE OR REPLACE
PROCEDURE inserir_Categoria (
  v_nome IN VARCHAR,
  v_data_selectivas IN DATE,
  v_data_quartas_de_Final IN DATE,
  v_data_semifinais IN DATE,
  v_data_final IN DATE
)
IS
  v_Id NUMBER;
BEGIN
  SELECT Categoria_Indice.NEXTVAL INTO v_Id FROM dual;

  -- inserir categoria com datas
  INSERT INTO Categoria (Id, Nome, Data_selectivas, Data_quartas_de_final, Data_semifinais, Data_final) VALUES
    (v_Id, v_nome, v_data_selectivas, v_data_quartas_de_final, v_data_semifinais, v_data_final);
  
  -- inserir baterias de
  -- 1) selectivas
  inserir_Bateria(v_Id, 4, 1);
  inserir_Bateria(v_Id, 4, 2);
  inserir_Bateria(v_Id, 4, 3);
  inserir_Bateria(v_Id, 4, 4);
  inserir_Bateria(v_Id, 4, 5);
  inserir_Bateria(v_Id, 4, 6);
  inserir_Bateria(v_Id, 4, 7);
  inserir_Bateria(v_Id, 4, 8);
  inserir_Bateria(v_Id, 4, 9);
  inserir_Bateria(v_Id, 4, 10);
  inserir_Bateria(v_Id, 4, 11);
  inserir_Bateria(v_Id, 4, 12);
  -- 2) quartas de final
  inserir_Bateria(v_Id, 3, 1);
  inserir_Bateria(v_Id, 3, 2);
  inserir_Bateria(v_Id, 3, 3);
  inserir_Bateria(v_Id, 3, 4);
  inserir_Bateria(v_Id, 3, 5);
  inserir_Bateria(v_Id, 3, 6);
  inserir_Bateria(v_Id, 3, 7);
  inserir_Bateria(v_Id, 3, 8);
  -- 3) semifinais
  inserir_Bateria(v_Id, 2, 1);
  inserir_Bateria(v_Id, 2, 2);
  inserir_Bateria(v_Id, 2, 3);
  inserir_Bateria(v_Id, 2, 4);
  -- 4) final
  inserir_Bateria(v_Id, 1, 1);
END inserir_Categoria;


CREATE OR REPLACE
PROCEDURE excluir_Categoria(
  v_Id IN NUMBER
)
IS
BEGIN
  -- Execução vai falhar se inscrições foram feito numa bateria
  DELETE FROM Bateria WHERE Categoria = v_Id;
  
  DELETE FROM Categoria WHERE Id = v_Id;
END excluir_Categoria;


CREATE OR REPLACE
VIEW Bateria_Info AS
  SELECT
    Bateria.Id,
    Bateria.Categoria,
    Bateria.Tipo,
    Bateria.Numero,
    Bateria.Fechado,
    -- escolhe a data da bateria por tipo dela
    (CASE
      WHEN Tipo = 1 THEN Data_final
      WHEN Tipo = 2 THEN Data_semifinais
      WHEN Tipo = 3 THEN Data_quartas_de_final
      WHEN Tipo = 4 THEN Data_selectivas
     END) AS Data,
    -- conta as participacoes na bateria
    (SELECT COUNT(*)
     FROM Participacoes
     WHERE Bateria = Bateria.Id
     GROUP BY Bateria) AS Participantes,
    -- conta as participacoes com resultado na bateria
    (SELECT SUM(CASE WHEN (Desclassificado IS NOT NULL OR Tempo IS NOT NULL) THEN 1 ELSE 0 END)
     FROM Participacoes
     WHERE Bateria = Bateria.Id
     GROUP BY Bateria) AS Resultados
  FROM Bateria, Categoria
  WHERE Bateria.Categoria = Categoria.Id;