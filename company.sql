PGDMP         7                z           jakimczyk.company    14.2    14.2     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'BIG5';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    16394    jakimczyk.company    DATABASE     o   CREATE DATABASE "jakimczyk.company" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Polish_Poland.1250';
 #   DROP DATABASE "jakimczyk.company";
                postgres    false            ?            1259    16395 	   employees    TABLE     ?  CREATE TABLE public.employees (
    pesel character varying(11) NOT NULL,
    first_name character varying(20) NOT NULL,
    last_name character varying(40) NOT NULL,
    job character varying(50) NOT NULL,
    team smallint NOT NULL,
    salary numeric(10,2) NOT NULL,
    phone_nr character varying(11) NOT NULL,
    bonus_salary numeric(7,2),
    nr_of_card character varying(16),
    provision numeric(7,2),
    limit_of_provision numeric(7,2)
);
    DROP TABLE public.employees;
       public         heap    postgres    false            ?            1259    16412    teams    TABLE     <   CREATE TABLE public.teams (
    team_id integer NOT NULL
);
    DROP TABLE public.teams;
       public         heap    postgres    false            ?          0    16395 	   employees 
   TABLE DATA           ?   COPY public.employees (pesel, first_name, last_name, job, team, salary, phone_nr, bonus_salary, nr_of_card, provision, limit_of_provision) FROM stdin;
    public          postgres    false    209   ?       ?          0    16412    teams 
   TABLE DATA           (   COPY public.teams (team_id) FROM stdin;
    public          postgres    false    210   E       `           2606    16399    employees employees_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (pesel);
 B   ALTER TABLE ONLY public.employees DROP CONSTRAINT employees_pkey;
       public            postgres    false    209            b           2606    16416    teams teams_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.teams
    ADD CONSTRAINT teams_pkey PRIMARY KEY (team_id);
 :   ALTER TABLE ONLY public.teams DROP CONSTRAINT teams_pkey;
       public            postgres    false    210            c           2606    16425    employees employees_team_fkey    FK CONSTRAINT     ~   ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_team_fkey FOREIGN KEY (team) REFERENCES public.teams(team_id);
 G   ALTER TABLE ONLY public.employees DROP CONSTRAINT employees_team_fkey;
       public          postgres    false    209    3170    210            ?   ?   x?U??j?0??????Ȓ??B?V6???1???4?H??????Y??:l}:?9???8G?????/X??p
??;??>?Wa?M
0-?J??6O?#YJFD???Oñ??Ʈ>???߭?!???Lb8<??"b??ް?CC?b?Ǧ?I?t1L>yx?6??O ??L6??1???/??*?C??2????}̘?????zx?Ԧ??f?S?3?X?yAΫ?[_??٦????R9Z1t?g?B?M?Y?      ?      x?3?2?2?????? ?]     