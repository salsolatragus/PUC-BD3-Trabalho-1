CREATE TABLE Categoria (
  Id INTEGER PRIMARY KEY,
  Nome VARCHAR(100) NOT NULL,
  Data_Selectivas DATE NOT NULL,
  Data_Quartas_de_Final DATE NOT NULL,
  Data_Semifinais DATE NOT NULL,
  Data_Final DATE NOT NULL,
  
  CHECK (Data_Selectivas < Data_Quartas_de_Final),
  CHECK (Data_Quartas_de_Final < Data_Semifinais),
  CHECK (Data_Semifinais < Data_Final)
);

CREATE TABLE Bateria (
  Id INTEGER PRIMARY KEY,
  Categoria INTEGER NOT NULL,
  Tipo INTEGER NOT NULL,
  Numero INTEGER NOT NULL,
  
  UNIQUE (Categoria, Tipo, Numero),
  CONSTRAINT fk_Categoria FOREIGN KEY (Categoria) REFERENCES Categoria(Id),
  
  CHECK (Numero > 0),
  CHECK (Tipo > 0 AND Tipo <= 4),
  CHECK (Tipo <> 1 OR Numero <= 1),
  CHECK (Tipo <> 2 OR Numero <= 4),
  CHECK (Tipo <> 3 OR Numero <= 8),
  CHECK (Tipo <> 4 OR Numero <= 12)
);

CREATE TABLE Competidor (
  Id INTEGER PRIMARY KEY,
  Nome VARCHAR(100) NOT NULL,
  Nacionalidade VARCHAR(3) NOT NULL,
  Data_de_Nacimanto DATE NOT NULL
);

CREATE TABLE Participacoes (
  Bateria INTEGER NOT NULL,
  Competidor INTEGER NOT NULL,
  Desclassificado INTEGER,
  Tempo INTEGER,
  
  PRIMARY KEY (Bateria, Competidor),
  CONSTRAINT fk_Bateria FOREIGN KEY (Bateria) REFERENCES Bateria(Id),
  CONSTRAINT fk_Competidor FOREIGN KEY (Competidor) REFERENCES Competidor(Id),
  CHECK (Desclassificado = NULL OR Desclassificado <= 1)
);