PGDMP     ,            
    	    x            docente    10.6    11.3     �
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �
           1262    77014    docente    DATABASE     �   CREATE DATABASE docente WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';
    DROP DATABASE docente;
             postgres    false                        2615    77015    docente    SCHEMA        CREATE SCHEMA docente;
    DROP SCHEMA docente;
             postgres    false            �            1259    77018    tbl_docente    TABLE       CREATE TABLE docente.tbl_docente (
    id bigint NOT NULL,
    cedula text NOT NULL,
    nombre character varying(30) NOT NULL,
    apellido character varying(30) NOT NULL,
    correo text NOT NULL,
    fecha date NOT NULL,
    materias character varying
);
     DROP TABLE docente.tbl_docente;
       docente         postgres    false    4            �            1259    77016    tbl_docente_id_seq    SEQUENCE     |   CREATE SEQUENCE docente.tbl_docente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE docente.tbl_docente_id_seq;
       docente       postgres    false    197    4            �
           0    0    tbl_docente_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE docente.tbl_docente_id_seq OWNED BY docente.tbl_docente.id;
            docente       postgres    false    196            o
           2604    77021    tbl_docente id    DEFAULT     r   ALTER TABLE ONLY docente.tbl_docente ALTER COLUMN id SET DEFAULT nextval('docente.tbl_docente_id_seq'::regclass);
 >   ALTER TABLE docente.tbl_docente ALTER COLUMN id DROP DEFAULT;
       docente       postgres    false    196    197    197            �
          0    77018    tbl_docente 
   TABLE DATA               ]   COPY docente.tbl_docente (id, cedula, nombre, apellido, correo, fecha, materias) FROM stdin;
    docente       postgres    false    197   �       �
           0    0    tbl_docente_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('docente.tbl_docente_id_seq', 39, true);
            docente       postgres    false    196            q
           2606    77026    tbl_docente pk_id_docente 
   CONSTRAINT     X   ALTER TABLE ONLY docente.tbl_docente
    ADD CONSTRAINT pk_id_docente PRIMARY KEY (id);
 D   ALTER TABLE ONLY docente.tbl_docente DROP CONSTRAINT pk_id_docente;
       docente         postgres    false    197            �
   �   x����
�0���S�̶$C�Mq�Bi#���1��A(w���}���j!�(�l\c�7���\�0���⤼�.Dxf@�n제�t ��cZ�D���=��S'��dB�3:�Mj�B6rA�^��o�.����#]����Э>X��F�M�W�U��*�b�     