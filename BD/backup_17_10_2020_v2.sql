PGDMP     *                	    x            docente    10.6    11.3 *    #           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            $           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            %           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            &           1262    77099    docente    DATABASE     �   CREATE DATABASE docente WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';
    DROP DATABASE docente;
             postgres    false                        2615    77100    docentes    SCHEMA        CREATE SCHEMA docentes;
    DROP SCHEMA docentes;
             postgres    false                        2615    77137 
   estudiante    SCHEMA        CREATE SCHEMA estudiante;
    DROP SCHEMA estudiante;
             postgres    false                        2615    77101    materias    SCHEMA        CREATE SCHEMA materias;
    DROP SCHEMA materias;
             postgres    false            �            1259    77102    tbl_docente    TABLE     )  CREATE TABLE docentes.tbl_docente (
    id integer NOT NULL,
    apellido character varying(30) NOT NULL,
    cedula character varying(10) NOT NULL,
    correo character varying(255) NOT NULL,
    fecha_nacimiento timestamp without time zone NOT NULL,
    nombre character varying(30) NOT NULL
);
 !   DROP TABLE docentes.tbl_docente;
       docentes         postgres    false    4            �            1259    77105    tbl_docente_id_seq    SEQUENCE     �   CREATE SEQUENCE docentes.tbl_docente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE docentes.tbl_docente_id_seq;
       docentes       postgres    false    199    4            '           0    0    tbl_docente_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE docentes.tbl_docente_id_seq OWNED BY docentes.tbl_docente.id;
            docentes       postgres    false    200            �            1259    77140    tbl_estudiante    TABLE     �   CREATE TABLE estudiante.tbl_estudiante (
    id integer NOT NULL,
    id_docente integer NOT NULL,
    nombre character varying(30) NOT NULL,
    apellido character varying(30) NOT NULL
);
 &   DROP TABLE estudiante.tbl_estudiante;
    
   estudiante         postgres    false    6            �            1259    77138    tbl_estudiante_id_seq    SEQUENCE     �   CREATE SEQUENCE estudiante.tbl_estudiante_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE estudiante.tbl_estudiante_id_seq;
    
   estudiante       postgres    false    205    6            (           0    0    tbl_estudiante_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE estudiante.tbl_estudiante_id_seq OWNED BY estudiante.tbl_estudiante.id;
         
   estudiante       postgres    false    204            �            1259    77107    tbl_docente_materia    TABLE     p   CREATE TABLE materias.tbl_docente_materia (
    id_materia integer NOT NULL,
    id_docente integer NOT NULL
);
 )   DROP TABLE materias.tbl_docente_materia;
       materias         postgres    false    7            �            1259    77110    tbl_materia    TABLE     j   CREATE TABLE materias.tbl_materia (
    id integer NOT NULL,
    nombre character varying(30) NOT NULL
);
 !   DROP TABLE materias.tbl_materia;
       materias         postgres    false    7            �            1259    77113    tbl_materia_id_seq    SEQUENCE     �   CREATE SEQUENCE materias.tbl_materia_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE materias.tbl_materia_id_seq;
       materias       postgres    false    7    202            )           0    0    tbl_materia_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE materias.tbl_materia_id_seq OWNED BY materias.tbl_materia.id;
            materias       postgres    false    203            �            1259    77158 
   estudiante    TABLE     ;   CREATE TABLE public.estudiante (
    id bigint NOT NULL
);
    DROP TABLE public.estudiante;
       public         postgres    false            �            1259    77163    sequence    TABLE     k   CREATE TABLE public.sequence (
    seq_name character varying(50) NOT NULL,
    seq_count numeric(38,0)
);
    DROP TABLE public.sequence;
       public         postgres    false            �
           2604    77115    tbl_docente id    DEFAULT     t   ALTER TABLE ONLY docentes.tbl_docente ALTER COLUMN id SET DEFAULT nextval('docentes.tbl_docente_id_seq'::regclass);
 ?   ALTER TABLE docentes.tbl_docente ALTER COLUMN id DROP DEFAULT;
       docentes       postgres    false    200    199            �
           2604    77147    tbl_estudiante id    DEFAULT     ~   ALTER TABLE ONLY estudiante.tbl_estudiante ALTER COLUMN id SET DEFAULT nextval('estudiante.tbl_estudiante_id_seq'::regclass);
 D   ALTER TABLE estudiante.tbl_estudiante ALTER COLUMN id DROP DEFAULT;
    
   estudiante       postgres    false    205    204    205            �
           2604    77116    tbl_materia id    DEFAULT     t   ALTER TABLE ONLY materias.tbl_materia ALTER COLUMN id SET DEFAULT nextval('materias.tbl_materia_id_seq'::regclass);
 ?   ALTER TABLE materias.tbl_materia ALTER COLUMN id DROP DEFAULT;
       materias       postgres    false    203    202                      0    77102    tbl_docente 
   TABLE DATA               _   COPY docentes.tbl_docente (id, apellido, cedula, correo, fecha_nacimiento, nombre) FROM stdin;
    docentes       postgres    false    199   9.                 0    77140    tbl_estudiante 
   TABLE DATA               N   COPY estudiante.tbl_estudiante (id, id_docente, nombre, apellido) FROM stdin;
 
   estudiante       postgres    false    205   �.                 0    77107    tbl_docente_materia 
   TABLE DATA               G   COPY materias.tbl_docente_materia (id_materia, id_docente) FROM stdin;
    materias       postgres    false    201   �.                 0    77110    tbl_materia 
   TABLE DATA               3   COPY materias.tbl_materia (id, nombre) FROM stdin;
    materias       postgres    false    202   �.                 0    77158 
   estudiante 
   TABLE DATA               (   COPY public.estudiante (id) FROM stdin;
    public       postgres    false    206   /                  0    77163    sequence 
   TABLE DATA               7   COPY public.sequence (seq_name, seq_count) FROM stdin;
    public       postgres    false    207   #/       *           0    0    tbl_docente_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('docentes.tbl_docente_id_seq', 26, true);
            docentes       postgres    false    200            +           0    0    tbl_estudiante_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('estudiante.tbl_estudiante_id_seq', 14, true);
         
   estudiante       postgres    false    204            ,           0    0    tbl_materia_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('materias.tbl_materia_id_seq', 4, true);
            materias       postgres    false    203            �
           2606    77118 "   tbl_docente tbl_docente_cedula_key 
   CONSTRAINT     a   ALTER TABLE ONLY docentes.tbl_docente
    ADD CONSTRAINT tbl_docente_cedula_key UNIQUE (cedula);
 N   ALTER TABLE ONLY docentes.tbl_docente DROP CONSTRAINT tbl_docente_cedula_key;
       docentes         postgres    false    199            �
           2606    77120 "   tbl_docente tbl_docente_correo_key 
   CONSTRAINT     a   ALTER TABLE ONLY docentes.tbl_docente
    ADD CONSTRAINT tbl_docente_correo_key UNIQUE (correo);
 N   ALTER TABLE ONLY docentes.tbl_docente DROP CONSTRAINT tbl_docente_correo_key;
       docentes         postgres    false    199            �
           2606    77122    tbl_docente tbl_docente_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY docentes.tbl_docente
    ADD CONSTRAINT tbl_docente_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY docentes.tbl_docente DROP CONSTRAINT tbl_docente_pkey;
       docentes         postgres    false    199            �
           2606    77152     tbl_estudiante tbl_estudiante_pk 
   CONSTRAINT     b   ALTER TABLE ONLY estudiante.tbl_estudiante
    ADD CONSTRAINT tbl_estudiante_pk PRIMARY KEY (id);
 N   ALTER TABLE ONLY estudiante.tbl_estudiante DROP CONSTRAINT tbl_estudiante_pk;
    
   estudiante         postgres    false    205            �
           2606    77124 ,   tbl_docente_materia tbl_docente_materia_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY materias.tbl_docente_materia
    ADD CONSTRAINT tbl_docente_materia_pkey PRIMARY KEY (id_materia, id_docente);
 X   ALTER TABLE ONLY materias.tbl_docente_materia DROP CONSTRAINT tbl_docente_materia_pkey;
       materias         postgres    false    201    201            �
           2606    77126    tbl_materia tbl_materia_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY materias.tbl_materia
    ADD CONSTRAINT tbl_materia_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY materias.tbl_materia DROP CONSTRAINT tbl_materia_pkey;
       materias         postgres    false    202            �
           2606    77162    estudiante estudiante_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.estudiante DROP CONSTRAINT estudiante_pkey;
       public         postgres    false    206            �
           2606    77167    sequence sequence_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.sequence
    ADD CONSTRAINT sequence_pkey PRIMARY KEY (seq_name);
 @   ALTER TABLE ONLY public.sequence DROP CONSTRAINT sequence_pkey;
       public         postgres    false    207            �
           2606    77153     tbl_estudiante tbl_estudiante_fk    FK CONSTRAINT     �   ALTER TABLE ONLY estudiante.tbl_estudiante
    ADD CONSTRAINT tbl_estudiante_fk FOREIGN KEY (id_docente) REFERENCES docentes.tbl_docente(id);
 N   ALTER TABLE ONLY estudiante.tbl_estudiante DROP CONSTRAINT tbl_estudiante_fk;
    
   estudiante       postgres    false    2705    205    199            �
           2606    77127 5   tbl_docente_materia fk_tbl_docente_materia_id_docente    FK CONSTRAINT     �   ALTER TABLE ONLY materias.tbl_docente_materia
    ADD CONSTRAINT fk_tbl_docente_materia_id_docente FOREIGN KEY (id_docente) REFERENCES docentes.tbl_docente(id);
 a   ALTER TABLE ONLY materias.tbl_docente_materia DROP CONSTRAINT fk_tbl_docente_materia_id_docente;
       materias       postgres    false    2705    201    199            �
           2606    77132 5   tbl_docente_materia fk_tbl_docente_materia_id_materia    FK CONSTRAINT     �   ALTER TABLE ONLY materias.tbl_docente_materia
    ADD CONSTRAINT fk_tbl_docente_materia_id_materia FOREIGN KEY (id_materia) REFERENCES materias.tbl_materia(id);
 a   ALTER TABLE ONLY materias.tbl_docente_materia DROP CONSTRAINT fk_tbl_docente_materia_id_materia;
       materias       postgres    false    202    2709    201               Q   x�3�N�KL*�L�4400�05553�LN����713uH�M���K���4J����*X��3X��c^JQj1W� ��g         "   x�3�4�L�,���t�L+M�+I-����� fWP            x������ � �            x������ � �            x������ � �             x�v�ww��4������ T     