/*
 Navicat Premium Data Transfer

 Source Server         : documentacion
 Source Server Type    : PostgreSQL
 Source Server Version : 90600
 Source Host           : localhost
 Source Database       : FIMDocumentos
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90600
 File Encoding         : utf-8

 Date: 01/22/2017 08:22:25 AM
*/

-- ----------------------------
--  Sequence structure for object_id_seq
-- ----------------------------
CREATE SEQUENCE "object_id_seq" INCREMENT 1 START 186 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;

-- ----------------------------
--  Table structure for usuario
-- ----------------------------
CREATE TABLE "usuario" (
	"id" int4 NOT NULL,
	"contraseña" varchar COLLATE "default",
	"isAdministrator" bool,
	"enabled" bool,
	"idArea" int4,
	"root" bool
)
WITH (OIDS=FALSE);

-- ----------------------------
--  Records of usuario
-- ----------------------------
BEGIN;
INSERT INTO "usuario" VALUES ('136', 'tesorero', 't', 't', '127', 'f');
INSERT INTO "usuario" VALUES ('139', 'secretaria', 'f', 't', '130', 'f');
INSERT INTO "usuario" VALUES ('138', 'usuarioArea4', 'f', 't', '129', 'f');
INSERT INTO "usuario" VALUES ('137', 'usuarioArea3', 'f', 't', '128', 'f');
INSERT INTO "usuario" VALUES ('9', null, null, null, null, 'f');
INSERT INTO "usuario" VALUES ('155', 'root2', 't', 't', '123', 't');
INSERT INTO "usuario" VALUES ('140', 'direccion2', 't', 'f', '123', 'f');
INSERT INTO "usuario" VALUES ('124', 'root', 't', 't', '123', 't');
INSERT INTO "usuario" VALUES ('125', 'coordinadoracademico', 'f', 't', '126', 'f');
COMMIT;

-- ----------------------------
--  Table structure for object
-- ----------------------------
CREATE TABLE "object" (
	"id" int4 NOT NULL DEFAULT nextval('object_id_seq'::regclass),
	"name" varchar COLLATE "default",
	"description" text COLLATE "default",
	"createdOn" timestamp(6) NULL DEFAULT now(),
	"createdBy" int4,
	"color" varchar COLLATE "default",
	"kind" varchar COLLATE "default"
)
WITH (OIDS=FALSE);

-- ----------------------------
--  Records of object
-- ----------------------------
BEGIN;
INSERT INTO "object" VALUES ('140', 'direccion2', null, '2017-01-11 19:27:45.830832', '124', null, null);
INSERT INTO "object" VALUES ('157', 'Area de superusuarios', null, '2017-01-15 19:44:41.615557', '124', null, null);
INSERT INTO "object" VALUES ('128', 'Jefaturas de carrera', null, '2017-01-10 21:40:00.649318', '124', null, null);
INSERT INTO "object" VALUES ('133', 'informe', null, '2017-01-11 09:34:12.118052', '124', null, 'keyword');
INSERT INTO "object" VALUES ('132', 'enero', null, '2017-01-11 09:34:12.109452', '124', null, 'keyword');
INSERT INTO "object" VALUES ('134', 'palabras', null, '2017-01-11 09:34:32.047557', '124', null, 'keyword');
INSERT INTO "object" VALUES ('137', 'usuarioArea3', '', '2017-01-11 19:19:22.243116', '124', null, null);
INSERT INTO "object" VALUES ('138', 'usuarioArea4', '', '2017-01-11 19:20:12.925937', '124', null, null);
INSERT INTO "object" VALUES ('142', 'area2', null, '2017-01-11 22:08:42.233543', '136', null, 'keyword');
INSERT INTO "object" VALUES ('144', 'de area 5', null, '2017-01-11 22:14:58.413576', '139', null, 'keyword');
INSERT INTO "object" VALUES ('139', 'secretaria', null, '2017-01-11 19:21:09.010166', '124', null, null);
INSERT INTO "object" VALUES ('136', 'tesorero', null, '2017-01-11 19:15:57.637905', '124', null, null);
INSERT INTO "object" VALUES ('148', 'diciembre', null, '2017-01-12 16:10:14.173322', '124', null, 'keyword');
INSERT INTO "object" VALUES ('150', 'inteligencia artificial', null, '2017-01-12 16:35:21.29638', '124', null, 'keyword');
INSERT INTO "object" VALUES ('127', 'Coordinación administrativa', null, '2017-01-10 21:39:55.83921', '124', null, null);
INSERT INTO "object" VALUES ('129', 'Tutorias', null, '2017-01-10 21:40:06.294055', '124', null, null);
INSERT INTO "object" VALUES ('152', 'captura', null, '2017-01-12 17:17:20.586458', '124', null, 'keyword');
INSERT INTO "object" VALUES ('153', 'prueba', null, '2017-01-12 17:17:20.587463', '124', null, 'keyword');
INSERT INTO "object" VALUES ('154', 'clave', null, '2017-01-12 17:18:09.788549', '124', null, 'keyword');
INSERT INTO "object" VALUES ('155', 'root2', '', '2017-01-15 11:24:12.97042', '124', null, null);
INSERT INTO "object" VALUES ('156', 'abc2', null, '2017-01-15 18:41:54.172433', '124', null, 'keyword');
INSERT INTO "object" VALUES ('146', 'documento', null, '2017-01-11 22:32:12.274006', '136', null, 'keyword');
INSERT INTO "object" VALUES ('123', 'Dirección', null, '2017-01-10 20:18:27.652247', '9', null, null);
INSERT INTO "object" VALUES ('130', 'Secretaria', null, '2017-01-10 21:40:11.575524', '124', null, null);
INSERT INTO "object" VALUES ('160', 'tutorias', null, '2017-01-16 20:15:09.072381', '124', null, 'keyword');
INSERT INTO "object" VALUES ('185', 'd1', 'd1', '2017-01-21 17:00:16.321775', '124', '#01579b', 'document');
INSERT INTO "object" VALUES ('186', 'd2', 'd2', '2017-01-21 17:00:33.588267', '124', '#01579b', 'document');
INSERT INTO "object" VALUES ('124', 'root', null, '2017-01-10 20:18:49.060262', '9', null, null);
INSERT INTO "object" VALUES ('126', 'Coodinación académica', null, '2017-01-10 21:39:51.234457', '124', null, null);
INSERT INTO "object" VALUES ('125', 'coordinadoracademico', null, '2017-01-10 21:23:09.250603', '124', null, null);
COMMIT;

-- ----------------------------
--  Table structure for keyword
-- ----------------------------
CREATE TABLE "keyword" (
	"id" int4
)
WITH (OIDS=FALSE);

-- ----------------------------
--  Records of keyword
-- ----------------------------
BEGIN;
INSERT INTO "keyword" VALUES ('133');
INSERT INTO "keyword" VALUES ('132');
INSERT INTO "keyword" VALUES ('134');
INSERT INTO "keyword" VALUES ('135');
INSERT INTO "keyword" VALUES ('142');
INSERT INTO "keyword" VALUES ('144');
INSERT INTO "keyword" VALUES ('146');
INSERT INTO "keyword" VALUES ('148');
INSERT INTO "keyword" VALUES ('150');
INSERT INTO "keyword" VALUES ('152');
INSERT INTO "keyword" VALUES ('153');
INSERT INTO "keyword" VALUES ('154');
INSERT INTO "keyword" VALUES ('156');
INSERT INTO "keyword" VALUES ('160');
COMMIT;

-- ----------------------------
--  Table structure for document
-- ----------------------------
CREATE TABLE "document" (
	"id" int4 NOT NULL,
	"fileName" varchar NOT NULL COLLATE "default",
	"fileDate" date,
	"idArea" int4
)
WITH (OIDS=FALSE);

-- ----------------------------
--  Records of document
-- ----------------------------
BEGIN;
INSERT INTO "document" VALUES ('185', 'Desarrollo – IA.docx', '2017-01-21', '157');
INSERT INTO "document" VALUES ('186', 'Desarrollo – IA(1).docx', '2017-01-21', '157');
COMMIT;

-- ----------------------------
--  Table structure for areaRelationships
-- ----------------------------
CREATE TABLE "areaRelationships" (
	"idArea1" int4 NOT NULL,
	"idArea2" int4 NOT NULL
)
WITH (OIDS=FALSE);

-- ----------------------------
--  Records of areaRelationships
-- ----------------------------
BEGIN;
INSERT INTO "areaRelationships" VALUES ('123', '128');
INSERT INTO "areaRelationships" VALUES ('123', '127');
INSERT INTO "areaRelationships" VALUES ('126', '127');
INSERT INTO "areaRelationships" VALUES ('126', '128');
INSERT INTO "areaRelationships" VALUES ('127', '130');
INSERT INTO "areaRelationships" VALUES ('130', '127');
INSERT INTO "areaRelationships" VALUES ('130', '128');
INSERT INTO "areaRelationships" VALUES ('123', '126');
INSERT INTO "areaRelationships" VALUES ('123', '129');
INSERT INTO "areaRelationships" VALUES ('123', '130');
INSERT INTO "areaRelationships" VALUES ('130', '123');
INSERT INTO "areaRelationships" VALUES ('128', '130');
INSERT INTO "areaRelationships" VALUES ('127', '128');
INSERT INTO "areaRelationships" VALUES ('127', '126');
INSERT INTO "areaRelationships" VALUES ('127', '123');
INSERT INTO "areaRelationships" VALUES ('130', '126');
INSERT INTO "areaRelationships" VALUES ('128', '126');
COMMIT;

-- ----------------------------
--  Table structure for documentKeywordRelationship
-- ----------------------------
CREATE TABLE "documentKeywordRelationship" (
	"idKeyword" int4,
	"idDocument" int4
)
WITH (OIDS=FALSE);

-- ----------------------------
--  Table structure for area
-- ----------------------------
CREATE TABLE "area" (
	"id" int4 NOT NULL,
	"superuser" bool,
	"enabled" bool
)
WITH (OIDS=FALSE);

-- ----------------------------
--  Records of area
-- ----------------------------
BEGIN;
INSERT INTO "area" VALUES ('0', null, null);
INSERT INTO "area" VALUES ('127', 'f', 't');
INSERT INTO "area" VALUES ('129', 'f', 't');
INSERT INTO "area" VALUES ('123', 't', 't');
INSERT INTO "area" VALUES ('130', 'f', 't');
INSERT INTO "area" VALUES ('157', 't', 't');
INSERT INTO "area" VALUES ('128', 'f', 'f');
INSERT INTO "area" VALUES ('126', 'f', 't');
COMMIT;


-- ----------------------------
--  Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "object_id_seq" RESTART 187 OWNED BY "object"."id";
-- ----------------------------
--  Primary key structure for table usuario
-- ----------------------------
ALTER TABLE "usuario" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table usuario
-- ----------------------------
CREATE UNIQUE INDEX  "usuario_id_key" ON "usuario" USING btree("id" "pg_catalog"."int4_ops" ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table object
-- ----------------------------
ALTER TABLE "object" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Uniques structure for table object
-- ----------------------------
ALTER TABLE "object" ADD CONSTRAINT "object_name_kind_key" UNIQUE ("name","kind") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table object
-- ----------------------------
CREATE UNIQUE INDEX  "object_id_key" ON "object" USING btree("id" "pg_catalog"."int4_ops" ASC NULLS LAST);

-- ----------------------------
--  Indexes structure for table keyword
-- ----------------------------
CREATE UNIQUE INDEX  "keyword_id_key" ON "keyword" USING btree("id" "pg_catalog"."int4_ops" ASC NULLS LAST);

-- ----------------------------
--  Indexes structure for table document
-- ----------------------------
CREATE UNIQUE INDEX  "document_id_key" ON "document" USING btree("id" "pg_catalog"."int4_ops" ASC NULLS LAST);

-- ----------------------------
--  Primary key structure for table areaRelationships
-- ----------------------------
ALTER TABLE "areaRelationships" ADD PRIMARY KEY ("idArea1", "idArea2") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table area
-- ----------------------------
ALTER TABLE "area" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table area
-- ----------------------------
CREATE UNIQUE INDEX  "area_id_key" ON "area" USING btree("id" "pg_catalog"."int4_ops" ASC NULLS LAST);

-- ----------------------------
--  Foreign keys structure for table usuario
-- ----------------------------
ALTER TABLE "usuario" ADD CONSTRAINT "usuario_idArea_fkey" FOREIGN KEY ("idArea") REFERENCES "area" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table object
-- ----------------------------
ALTER TABLE "object" ADD CONSTRAINT "fkCreatedByUsers" FOREIGN KEY ("createdBy") REFERENCES "usuario" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table document
-- ----------------------------
ALTER TABLE "document" ADD CONSTRAINT "documents_idObject_fkey" FOREIGN KEY ("id") REFERENCES "object" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "document" ADD CONSTRAINT "docuemnts_idArea_fkey" FOREIGN KEY ("idArea") REFERENCES "area" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table areaRelationships
-- ----------------------------
ALTER TABLE "areaRelationships" ADD CONSTRAINT "areaRelationships_idArea1_fkey" FOREIGN KEY ("idArea1") REFERENCES "area" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "areaRelationships" ADD CONSTRAINT "areaRelationships_idArea2_fkey" FOREIGN KEY ("idArea2") REFERENCES "area" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Foreign keys structure for table documentKeywordRelationship
-- ----------------------------
ALTER TABLE "documentKeywordRelationship" ADD CONSTRAINT "documentKeywordRelationship_idKeyword_fkey" FOREIGN KEY ("idKeyword") REFERENCES "keyword" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "documentKeywordRelationship" ADD CONSTRAINT "documentKeywordRelationship_idDocument_fkey" FOREIGN KEY ("idDocument") REFERENCES "document" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

