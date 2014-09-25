package dao;

import seguranca.Usuario;

public interface IDAOUsuario extends IDAOGenerico<Usuario> {
	public Usuario pegarUsuarioPeloLogin(String login) throws Exception;
}
