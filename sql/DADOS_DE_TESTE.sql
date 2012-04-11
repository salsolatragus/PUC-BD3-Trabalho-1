begin
  inserir_Categoria('Comp 1', '01-MAY-2012', '02-MAY-2012', '03-MAY-2012', '04-MAY-2012');
  inserir_Categoria('Comp 2', '01-MAY-2012', '02-MAY-2012', '03-MAY-2012', '04-MAY-2012');
  inserir_Categoria('Comp 3', '01-MAY-2012', '02-MAY-2012', '03-MAY-2012', '04-MAY-2012');
  inserir_Categoria('Comp 4', '01-MAY-2012', '02-MAY-2012', '03-MAY-2012', '04-MAY-2012');
  inserir_Categoria('Comp 5', '01-MAY-2012', '02-MAY-2012', '03-MAY-2012', '04-MAY-2012');
  inserir_Categoria('Comp 6', '01-MAY-2012', '02-MAY-2012', '03-MAY-2012', '04-MAY-2012');
  inserir_Categoria('Comp 7', '01-MAY-2012', '02-MAY-2012', '03-MAY-2012', '04-MAY-2012');
end;

SELECT * FROM Categoria;
SELECT * FROM Bateria WHERE Categoria = 7;

begin
  inserir_Competidor('Sven', 'GER', '20-JUN-1987');
  inserir_Competidor('A', 'BRA', '20-JUN-1987');
  inserir_Competidor('B', 'US', '20-JUN-1987');
  inserir_Competidor('C', 'FRA', '20-JUN-1987');
  inserir_Competidor('D', 'URU', '20-JUN-1987');
  inserir_Competidor('E', 'ARG', '20-JUN-1987');
  inserir_Competidor('F', 'CHI', '20-JUN-1987');
  inserir_Competidor('G', 'AUS', '20-JUN-1987');
  inserir_Competidor('h', 'GRK', '20-JUN-1987');
end;

SELECT * FROM Competidor;

begin
  registrar_por_Categoria(6, 1);
end;

SELECT * FROM Categoria_Competidor;
SELECT * FROM Participacoes;

begin
  --inserir_Resultado(6,1,6500);
  inserir_Desclassificacao(6,1);
end;

SELECT * FROM Bateria_Info;
SELECT * FROM Bateria_Aberta;

begin
  fechar_Bateria(1);
end;