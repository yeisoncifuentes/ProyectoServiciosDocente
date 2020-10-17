PGDMP     '                	    x            Docentes    11.4    11.4                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    74317    Docentes    DATABASE     �   CREATE DATABASE "Docentes" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Colombia.1252' LC_CTYPE = 'Spanish_Colombia.1252';
    DROP DATABASE "Docentes";
             postgres    false                        2615    74318    docentes    SCHEMA        CREATE SCHEMA docentes;
    DROP SCHEMA docentes;
             postgres    false                        2615    74330    materias    SCHEMA        CREATE SCHEMA materias;
    DROP SCHEMA materias;
             postgres    false            �            1259    74405    tbl_docente    TABLE     )  CREATE TABLE docentes.tbl_docente (
    id integer NOT NULL,
    apellido character varying(30) NOT NULL,
    cedula character varying(10) NOT NULL,
    correo character varying(255) NOT NULL,
    fecha_nacimiento timestamp without time zone NOT NULL,
    nombre character varying(30) NOT NULL
);
 !   DROP TABLE docentes.tbl_docente;
       docentes         postgres    false    6            �            1259    74403    tbl_docente_id_seq    SEQUENCE     �   CREATE SEQUENCE docentes.tbl_docente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE docentes.tbl_docente_id_seq;
       docentes       postgres    false    6    201                       0    0    tbl_docente_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE docentes.tbl_docente_id_seq OWNED BY docentes.tbl_docente.id;
            docentes       postgres    false    200            �            1259    74415    tbl_docente_materia    TABLE     p   CREATE TABLE materias.tbl_docente_materia (
    id_materia integer NOT NULL,
    id_docente integer NOT NULL
);
 )   DROP TABLE materias.tbl_docente_materia;
       materias         postgres    false    8            �            1259    74397    tbl_materia    TABLE     j   CREATE TABLE materias.tbl_materia (
    id integer NOT NULL,
    nombre character varying(30) NOT NULL
);
 !   DROP TABLE materias.tbl_materia;
       materias         postgres    false    8            �            1259    74395    tbl_materia_id_seq    SEQUENCE     �   CREATE SEQUENCE materias.tbl_materia_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE materias.tbl_materia_id_seq;
       materias       postgres    false    8    199                       0    0    tbl_materia_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE materias.tbl_materia_id_seq OWNED BY materias.tbl_materia.id;
            materias       postgres    false    198            �
           2604    74408    tbl_docente id    DEFAULT     t   ALTER TABLE ONLY docentes.tbl_docente ALTER COLUMN id SET DEFAULT nextval('docentes.tbl_docente_id_seq'::regclass);
 ?   ALTER TABLE docentes.tbl_docente ALTER COLUMN id DROP DEFAULT;
       docentes       postgres    false    200    201    201            �
           2604    74400    tbl_materia id    DEFAULT     t   ALTER TABLE ONLY materias.tbl_materia ALTER COLUMN id SET DEFAULT nextval('materias.tbl_materia_id_seq'::regclass);
 ?   ALTER TABLE materias.tbl_materia ALTER COLUMN id DROP DEFAULT;
       materias       postgres    false    198    199    199                      0    74405    tbl_docente 
   TABLE DATA               _   COPY docentes.tbl_docente (id, apellido, cedula, correo, fecha_nacimiento, nombre) FROM stdin;
    docentes       postgres    false    201   3                 0    74415    tbl_docente_materia 
   TABLE DATA               G   COPY materias.tbl_docente_materia (id_materia, id_docente) FROM stdin;
    materias       postgres    false    202   �                 0    74397    tbl_materia 
   TABLE DATA               3   COPY materias.tbl_materia (id, nombre) FROM stdin;
    materias       postgres    false    199   �                  0    0    tbl_docente_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('docentes.tbl_docente_id_seq', 4, true);
            docentes       postgres    false    200                       0    0    tbl_materia_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('materias.tbl_materia_id_seq', 2, true);
            materias       postgres    false    198            �
           2606    74412 "   tbl_docente tbl_docente_cedula_key 
   CONSTRAINT     a   ALTER TABLE ONLY docentes.tbl_docente
    ADD CONSTRAINT tbl_docente_cedula_key UNIQUE (cedula);
 N   ALTER TABLE ONLY docentes.tbl_docente DROP CONSTRAINT tbl_docente_cedula_key;
       docentes         postgres    false    201            �
           2606    74414 "   tbl_docente tbl_docente_correo_key 
   CONSTRAINT     a   ALTER TABLE ONLY docentes.tbl_docente
    ADD CONSTRAINT tbl_docente_correo_key UNIQUE (correo);
 N   ALTER TABLE ONLY docentes.tbl_docente DROP CONSTRAINT tbl_docente_correo_key;
       docentes         postgres    false    201            �
           2606    74410    tbl_docente tbl_docente_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY docentes.tbl_docente
    ADD CONSTRAINT tbl_docente_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY docentes.tbl_docente DROP CONSTRAINT tbl_docente_pkey;
       docentes         postgres    false    201            �
           2606    74419 ,   tbl_docente_materia tbl_docente_materia_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY materias.tbl_docente_materia
    ADD CONSTRAINT tbl_docente_materia_pkey PRIMARY KEY (id_materia, id_docente);
 X   ALTER TABLE ONLY materias.tbl_docente_materia DROP CONSTRAINT tbl_docente_materia_pkey;
       materias         postgres    false    202    202            �
           2606    74402    tbl_materia tbl_materia_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY materias.tbl_materia
    ADD CONSTRAINT tbl_materia_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY materias.tbl_materia DROP CONSTRAINT tbl_materia_pkey;
       materias         postgres    false    199            �
           2606    74425 5   tbl_docente_materia fk_tbl_docente_materia_id_docente    FK CONSTRAINT     �   ALTER TABLE ONLY materias.tbl_docente_materia
    ADD CONSTRAINT fk_tbl_docente_materia_id_docente FOREIGN KEY (id_docente) REFERENCES docentes.tbl_docente(id);
 a   ALTER TABLE ONLY materias.tbl_docente_materia DROP CONSTRAINT fk_tbl_docente_materia_id_docente;
       materias       postgres    false    2706    201    202            �
           2606    74420 5   tbl_docente_materia fk_tbl_docente_materia_id_materia    FK CONSTRAINT     �   ALTER TABLE ONLY materias.tbl_docente_materia
    ADD CONSTRAINT fk_tbl_docente_materia_id_materia FOREIGN KEY (id_materia) REFERENCES materias.tbl_materia(id);
 a   ALTER TABLE ONLY materias.tbl_docente_materia DROP CONSTRAINT fk_tbl_docente_materia_id_materia;
       materias       postgres    false    2700    199    202               [   x�3�N�KL*�L�4400�05553�LN����713uH�M���K���4J����*X��3X��c^JQj1�1�F%eW� '�            x�3�4����� k              x�3���K�I-�2�tN�I.������� U�W     