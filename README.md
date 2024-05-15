Aplicação web (Locadora de filmes)

Regras de negócio:
Um filme sempre pertence a uma categoria. Contudo, podem  existir categorias que ainda não tenham filmes vinculados.  
Um filme tem várias mídias para locação. Uma mídia sempre pertence a um filme, mas podem existir mídias de um filme que ainda não foram locadas.
Uma locação contém uma única mídia que está vinculada a um único cliente. 
Quando o cliente loca dois filmes, está levando duas mídias. Com isso teremos duas locações, já que a data de devolução pode ser diferente de um filme(mídia) para o outro. Um cliente pode ter ou não realizado uma locação.
Um cliente tem um único endereço. Seu código é também o código de seu endereço para identificação.
Um endereço pertence a um único cliente.

Tecnologias utilizadas:
Java, JavaServerFaces, JPA, Hibernate, Arquitetura baseada no modelo MVC, Apache TomCat, PostgreSQL e IDE Eclipse.
