
Fazer o mvn clean package para gerar o jar.
	Run as -> Run configurations -> mvn package
		Goals: clean package

add o contexto de aplicação e as variáveis de ambiente na linha de comando:
	$ java -jar -Dspring.profiles.active=prod -DFORUM_DATABASE_URL=jdbc:h2:mem:alura-forum -DFORUM_DATABASE_USERNAME=sa -DFORUM_DATABASE_PASSWORD= -DFORUM_JWT_SECRET=123456 forum.jar


exportar as variaveis de ambiente no terminal ou junto com o comando 'java -jar'. '$ echo VARIAVEL' para ver se já nao existe
	arquivo variaveisDeAmbiente.txt



Na raiz do projeto:
	$ docker build -t alura/forum .
	$ docker image list
	$ docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE='prod' -e FORUM_DATABASE_URL='jdbc:h2:mem:alura-forum' -e FORUM_DATABASE_USERNAME='sa' -e FORUM_DATABASE_PASSWORD='' -e FORUM_JWT_SECRET='123456' alura:forum 


Deploy de container no Heroku
	devcenter.heroku.com/articles/container-registry-and-runtime

	
Config Vars na INTERFACE GRÁFICA do Heroku:
	PORT=8080
	SPRING_PROFILES_ACTIVE=prod
	FORUM_DATABASE_URL=jdbc:h2:mem:alura-forum
	FORUM_DATABASE_USERNAME=sa
	FORUM_DATABASE_PASSWORD=
	FORUM_JWT_SECRET=A+X;fTJP&Pd,TD9dwVq(hsHX,ya^<wsD_UK7L+@=S;{'CydP]{v@}G'b>et;yz$*\yL5S8EJN:%P:X%H9>#nYLrX}@\s?CQcpspH,2emzBc!Q[V'AYa~uzF8WR~AUrMzxp/V$9([S9X#zj/CH('#]B_Hc+%fGhe27YB;^j4\Xk=Ju"Ap~_&<L;=!Z;!,2UP;!hF3P]j85#*`&T]/kB/W^6$v~u6qpejL>kY^f)sy4:qTq_Ec!-z!@aAp~sLKGU>$
	