
% This library is all we need to call Java from Prolog
:- module(dqs, [runDQS/3]).

:- multifile
	user:file_search_path/2.
:- dynamic
	user:file_search_path/2.

% :- load_files(library(setup), [silent(true)]).

:- absolute_file_name('dacura/applications', Dir),
   asserta(user:file_search_path(library, Dir)).

:- use_module(library(http/json)).
:- use_module(library(http/json_convert)).
:- use_module(library(semweb/rdf_db)).
:- use_module(library(semweb/turtle)).
:- use_module(library(checker)).
:- use_module(library(utils)).

loadDB(SchemaFile,Schema) :- 
    rdf_retractall(_, _, _, Schema),
    rdf_load(SchemaFile, [graph(Schema)]).

runDQS(SchemaFile,Tests,Result) :-
    loadDB(SchemaFile,schema),
    checker:schemaTest([schema=schema, tests=Tests],schema,Errors),
    jsonify(Errors,JSON),
    atom_json_term(Result, JSON, [as(atom)]).
