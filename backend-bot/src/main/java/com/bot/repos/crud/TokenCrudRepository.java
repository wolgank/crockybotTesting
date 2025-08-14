package com.bot.repos.crud;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bot.tables.ComandoTable;
import com.bot.tables.HistorialTable;
import com.bot.tables.TokenTable;
import com.bot.tables.UsuarioTable;

@Repository
public interface TokenCrudRepository extends CrudRepository<TokenTable,String> {
}
