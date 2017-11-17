package br.com.fish.devops.rest.test;

import java.util.Collection;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fish.devops.domain.cliente.Cliente;
import br.com.fish.devops.rest.ClienteRestService;
import junit.framework.AssertionFailedError;

public class ClienteTest {

	Cliente clienteTest1;
	Cliente clienteTest2;
	Cliente clienteTest3;
	Cliente clienteTest4;
	Cliente clienteTest5;
	ClienteRestService clienteRest;
	ClienteRestService clienteRestBackup; 

	@Before
	public void loadClientTest(){

		clienteTest1 = new Cliente();
		clienteTest1.setId(6);
		clienteTest1.setNome("Cliente 6");
		clienteTest1.setEmail("customer6@gmail.com");

		clienteTest2 = new Cliente();
		clienteTest2.setId(7);
		clienteTest2.setNome("Cliente 7");
		clienteTest2.setEmail("customer7@gmail.com");

		clienteTest3 = new Cliente();
		clienteTest3.setId(8);
		clienteTest3.setNome("Cliente 8");
		clienteTest3.setEmail("customer8@gmail.com");

		clienteTest4 = new Cliente();
		clienteTest4.setId(9);
		clienteTest4.setNome("Cliente 9");
		clienteTest4.setEmail("customer9@gmail.com");

		clienteTest5 = new Cliente();
		clienteTest5.setId(10);
		clienteTest5.setNome("Cliente 10");
		clienteTest5.setEmail("customer10@gmail.com");

		clienteRest = new ClienteRestService();
		clienteRestBackup = clienteRest;
	}

	//  Testando a busca por todos os clientes
	@Test
	public void getClientesTest(){
		Collection<Cliente> clienteList = clienteRest.getClientes();

		Assert.assertNotNull(clienteList);
	}

	//  Testando a busca por um cliente
	@Test
	public void getClienteTest(){
		Collection<Cliente> clienteList = clienteRest.getClientes();
		long id = clienteList.size();

		Cliente clienteListTest = (Cliente) clienteList.toArray()[(int) id - 1];
		Cliente clienteTest = clienteRest.getCliente(id);

		Assert.assertEquals(clienteTest.getNome(),clienteListTest.getNome());
		Assert.assertEquals(clienteTest.getEmail(),clienteListTest.getEmail());
	}

	//  Testando se a excessão de Cliente Inexistente está funcionando
	@Test
	public void getClienteInexistenteTest(){
		long id = 9;

		try {
			Cliente clienteTest = clienteRest.getCliente(id);
		} catch (Exception e) {
			Assert.assertEquals(e.getMessage(), "Cliente Não Existe!");
		}
	}

	// Testando adição de cliente
	@Test
	public void addClienteTest(){
		int countClientBefore = clienteRest.getClientes().size();
		clienteRest.addCliente(clienteTest2);

		Collection<Cliente> clienteListAfter = clienteRest.getClientes();
		Cliente clientTest = (Cliente) clienteListAfter.toArray()[clienteListAfter.size()-1];

		Assert.assertEquals(countClientBefore + 1, clienteListAfter.size());
		Assert.assertEquals(clienteTest2.getNome(), clientTest.getNome());
		Assert.assertEquals(clienteTest2.getEmail(), clientTest.getEmail());
	}

	// Testando adição de cliente Existente
	@Test
	public void addClienteExistenteTest(){
		
		clienteTest3.setId(1);
		try {
			clienteRest.addCliente(clienteTest3);
		} catch (Exception e) {
			Assert.assertEquals("Cliente já existe!", e.getMessage());
			clienteRest = new ClienteRestService();
		}		
	}
	

	// Testando merge de cliente 
	@Test
	public void mergeClienteTest(){
		long id = 3;
		Cliente clientTestBefore = clienteRest.getCliente(id);
		String nameClientBefore = clientTestBefore.getNome();
		String emailClientBefore = clientTestBefore.getEmail();
		
		clienteTest4.setId(id);
		
		clienteRest.mergeCliente(clienteTest4);
		Cliente clientTestAfter = clienteRest.getCliente(id);
		
		Assert.assertEquals(clientTestAfter.getNome(), clienteTest4.getNome());
		Assert.assertEquals(clientTestAfter.getEmail(), clienteTest4.getEmail());
		
		Assert.assertNotEquals(nameClientBefore, clientTestAfter.getNome());
		Assert.assertNotEquals(emailClientBefore, clientTestAfter.getEmail());
	}
	
	@After
	public void cleanTest(){
		clienteRest = clienteRestBackup;
	}

}
