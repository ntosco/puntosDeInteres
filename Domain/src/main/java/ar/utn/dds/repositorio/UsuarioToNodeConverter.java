package ar.utn.dds.repositorio;

import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.neo4j.graphdb.Node;

import ar.utn.dds.usuarios.Usuario;
import ar.utn.dds.usuarios.UsuarioConcreto;


public class UsuarioToNodeConverter {
	public static UsuarioConcreto convertToUsuario(final Node nodeUsuario) {
		UsuarioConcreto _usuario = new UsuarioConcreto();
		final Procedure1<UsuarioConcreto> _function = (UsuarioConcreto it) -> {
			int _id = (int) nodeUsuario.getId();
			it.setId(_id);
			Object _property = nodeUsuario.getProperty("nombreUsuario", "");
			it.setNombreUsuario(((String) _property));
			Object _property_1 = nodeUsuario.getProperty("password", "");
			it.setPassword(((String) _property_1));
		};
	return ObjectExtensions.<UsuarioConcreto>operator_doubleArrow(_usuario, _function);
	}
}
