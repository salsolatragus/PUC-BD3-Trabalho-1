CREATE SEQUENCE Competidor_indice;


CREATE OR REPLACE
PROCEDURE inserir_Competidor(
  v_nome VARCHAR,
  v_nacionalidade VARCHAR,
  v_data_de_nacimento DATE
)
IS
BEGIN
  INSERT INTO Competidor (Id, Nome, Nacionalidade, Data_de_nacimento) VALUES
    (Competidor_indice.NEXTVAL, v_nome, v_nacionalidade, v_data_de_nacimento);
END inserir_Competidor;


CREATE OR REPLACE
TRIGGER Limite_de_participantes
BEFORE INSERT ON Participacoes
FOR EACH ROW
DECLARE
  v_participantes NUMBER;
  participa_demais EXCEPTION;
BEGIN
  SELECT COUNT(*) INTO v_participantes
  FROM Participacoes
  WHERE Bateria = :NEW.Bateria;
  
  IF v_participantes >= 8 THEN
    RAISE participa_demais;
  END IF;
END Limite_de_participantes;


CREATE OR REPLACE
VIEW Bateria_aberta AS
  SELECT Id, Categoria, Tipo, Numero
  FROM Bateria_Info
  WHERE 8 > Participantes OR Participantes IS NULL;


CREATE OR REPLACE
TRIGGER Limite_de_inscricoes
BEFORE INSERT ON Participacoes
FOR EACH ROW
DECLARE
  v_inscricoes NUMBER;
  participa_demais EXCEPTION;
BEGIN
  SELECT COUNT(*) INTO v_inscricoes
  FROM Categoria_Competidor
  WHERE Competidor = :NEW.Competidor;
  
  IF v_inscricoes >= 4 THEN
    RAISE participa_demais;
  END IF;
END Limite_de_inscricoes;


CREATE OR REPLACE
PROCEDURE registrar_por_Categoria(
  v_competidor NUMBER,
  v_categoria NUMBER
)
IS
BEGIN
  registrar_por_Prova(v_competidor, 4, v_categoria);
END registrar_por_Categoria;


CREATE OR REPLACE
PROCEDURE registrar_por_Prova(
  v_competidor NUMBER,
  v_tipo NUMBER,
  v_categoria NUMBER
)
IS
  v_bateria_aberta NUMBER;
  prova_cheio EXCEPTION;
BEGIN
  SELECT Id INTO v_bateria_aberta
  FROM Bateria_aberta
  WHERE Categoria = v_categoria
    AND Tipo = v_tipo
    AND ROWNUM = 1
  ORDER BY Numero ASC;
  
  INSERT INTO Participacoes (Bateria, Competidor) VALUES
      (v_bateria_aberta, v_competidor);
EXCEPTION
  WHEN no_data_found THEN
    RAISE prova_cheio;
END registrar_por_Prova;


CREATE OR REPLACE
VIEW Categoria_Competidor AS
  SELECT Categoria.Id AS Categoria, Competidor.Id AS Competidor
  FROM Categoria, Bateria, Participacoes, Competidor
  WHERE Categoria.Id = Bateria.Categoria
    AND Bateria.Id = Participacoes.Bateria
    AND Participacoes.Competidor = Competidor.Id;