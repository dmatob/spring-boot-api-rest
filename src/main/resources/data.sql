INSERT INTO ARTICULO_TIPO (CODIGO, DESCRIPCION) VALUES
  ('ZAPATILLA', 'Zapatillas deportivas'),
  ('MALLA', 'Mallas deportivas'),
  ('CHAQUETA', 'Chaquetas deportivas'),
  ('BOTAS', 'Botas de montaña'),
  ('BANADOR', 'Bañador');

INSERT INTO ARTICULO (CODIGO, ID_ARTICULO_TIPO, DESCRIPCION, PRECIO_EUROS, ULTIMA_MODIFICACION) VALUES
  ('converse-boulevard', (SELECT ID FROM ARTICULO_TIPO WHERE CODIGO = 'ZAPATILLA'), 'Zapatillas deportivas Converse Boulevard para hombre', 49.99, NULL),
  ('asics-icon', (SELECT ID FROM ARTICULO_TIPO WHERE CODIGO = 'MALLA'), 'Mallas de running Ascis Icon para mujer', 54.99, NULL),
  ('the-north-face-quest', (SELECT ID FROM ARTICULO_TIPO WHERE CODIGO = 'CHAQUETA'), 'Chaqueta de hombre para deportes de montaña de la marca The North Face', 149.99, '2022-11-06 23.03.10'),
  ('adidas-terrex-ax3', (SELECT ID FROM ARTICULO_TIPO WHERE CODIGO = 'BOTAS'), 'Botas de montaña para hombre Adidas Terrex AX3', 159.99, NULL),
  ('puma-racerback', (SELECT ID FROM ARTICULO_TIPO WHERE CODIGO = 'BANADOR'), 'Bañador de la marca Puma para mujer', 34.99, NULL),
  ('botas-chiruca-montreal', (SELECT ID FROM ARTICULO_TIPO WHERE CODIGO = 'BOTAS'), 'Botas de montaña Chiruca para hombre', 119.99, '2022-10-21 11.24.11');