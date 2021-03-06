PGDMP         5                v            std-challenge    9.6.5    9.6.0     q	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            r	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            s	           1262    45889    std-challenge    DATABASE     m   CREATE DATABASE "std-challenge" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';
    DROP DATABASE "std-challenge";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            t	           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12655    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            u	           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    46179    customer    TABLE     �   CREATE TABLE customer (
    id bigint NOT NULL,
    login character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL
);
    DROP TABLE public.customer;
       public         postgres    false    3            �            1259    46187    hibernate_sequences    TABLE     m   CREATE TABLE hibernate_sequences (
    sequence_name character varying(255) NOT NULL,
    next_val bigint
);
 '   DROP TABLE public.hibernate_sequences;
       public         postgres    false    3            �            1259    46192    order_customer    TABLE     �   CREATE TABLE order_customer (
    id bigint NOT NULL,
    order_time timestamp without time zone,
    status integer,
    customer_id bigint NOT NULL
);
 "   DROP TABLE public.order_customer;
       public         postgres    false    3            �            1259    46197    order_customer_product_list    TABLE     y   CREATE TABLE order_customer_product_list (
    order_customer_id bigint NOT NULL,
    product_list_id bigint NOT NULL
);
 /   DROP TABLE public.order_customer_product_list;
       public         postgres    false    3            �            1259    46200    product    TABLE     u   CREATE TABLE product (
    id bigint NOT NULL,
    description character varying(255),
    price double precision
);
    DROP TABLE public.product;
       public         postgres    false    3            j	          0    46179    customer 
   TABLE DATA               6   COPY customer (id, login, name, password) FROM stdin;
    public       postgres    false    185   �       k	          0    46187    hibernate_sequences 
   TABLE DATA               ?   COPY hibernate_sequences (sequence_name, next_val) FROM stdin;
    public       postgres    false    186   5       l	          0    46192    order_customer 
   TABLE DATA               F   COPY order_customer (id, order_time, status, customer_id) FROM stdin;
    public       postgres    false    187   h       m	          0    46197    order_customer_product_list 
   TABLE DATA               R   COPY order_customer_product_list (order_customer_id, product_list_id) FROM stdin;
    public       postgres    false    188   �       n	          0    46200    product 
   TABLE DATA               2   COPY product (id, description, price) FROM stdin;
    public       postgres    false    189   �       �           2606    46186    customer customer_pkey 
   CONSTRAINT     M   ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public         postgres    false    185    185            �           2606    46191 ,   hibernate_sequences hibernate_sequences_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY hibernate_sequences
    ADD CONSTRAINT hibernate_sequences_pkey PRIMARY KEY (sequence_name);
 V   ALTER TABLE ONLY public.hibernate_sequences DROP CONSTRAINT hibernate_sequences_pkey;
       public         postgres    false    186    186            �           2606    46196 "   order_customer order_customer_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY order_customer
    ADD CONSTRAINT order_customer_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.order_customer DROP CONSTRAINT order_customer_pkey;
       public         postgres    false    187    187            �           2606    46204    product product_pkey 
   CONSTRAINT     K   ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public         postgres    false    189    189            �           2606    46215 7   order_customer_product_list fkeesajn88wkpnf61dtlrigtpuu    FK CONSTRAINT     �   ALTER TABLE ONLY order_customer_product_list
    ADD CONSTRAINT fkeesajn88wkpnf61dtlrigtpuu FOREIGN KEY (order_customer_id) REFERENCES order_customer(id);
 a   ALTER TABLE ONLY public.order_customer_product_list DROP CONSTRAINT fkeesajn88wkpnf61dtlrigtpuu;
       public       postgres    false    2287    188    187            �           2606    46210 7   order_customer_product_list fknf9qm3shooykwjtw3fvl9ixtx    FK CONSTRAINT     �   ALTER TABLE ONLY order_customer_product_list
    ADD CONSTRAINT fknf9qm3shooykwjtw3fvl9ixtx FOREIGN KEY (product_list_id) REFERENCES product(id);
 a   ALTER TABLE ONLY public.order_customer_product_list DROP CONSTRAINT fknf9qm3shooykwjtw3fvl9ixtx;
       public       postgres    false    188    2289    189            �           2606    46205 *   order_customer fkt9ws53lpvx6c4h3gp3k03duq3    FK CONSTRAINT     �   ALTER TABLE ONLY order_customer
    ADD CONSTRAINT fkt9ws53lpvx6c4h3gp3k03duq3 FOREIGN KEY (customer_id) REFERENCES customer(id);
 T   ALTER TABLE ONLY public.order_customer DROP CONSTRAINT fkt9ws53lpvx6c4h3gp3k03duq3;
       public       postgres    false    185    187    2283            j	   {   x�3�LL����t���%E�%�E1.#����"N����\ ��2�L,-��Kɇ�*F�*�*E������~�%�����~��))��iEiɕ�~F~>١ɩn����.�\1z\\\ �+,      k	   #   x�K.-.��M-�4��/JI-�O�	�q��qqq ̾
�      l	   !   x�3���4�4�21L�S�Ȉ���� U�      m	      x�3�4�2�=... �      n	   2   x�3�N�K)�L�Pp�4�2Bp��\c 7'1(e�ee;q�r��qqq �I_     