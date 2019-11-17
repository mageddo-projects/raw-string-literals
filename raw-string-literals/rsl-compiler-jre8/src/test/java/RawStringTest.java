import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.mdkt.compiler.InMemoryJavaCompiler;

import static com.mageddo.rawstringliterals.commons.StringUtils.align;
import static org.junit.Assert.assertEquals;

public class RawStringTest {

	@Test
	public void shouldCompileAndAndSetCommentTextToVariableValue() throws Exception {

		// arrange
		final InMemoryJavaCompiler compiler = InMemoryJavaCompiler.newInstance();


		final String sourceCode = IOUtils.toString(getClass().getResourceAsStream("/TestClass.java"));

		// act
		final Class clazz = compiler.compile("TestClass", sourceCode);
		final Object o = clazz.newInstance();

		// asssert
		final String sayHelloWord = String.valueOf(clazz.getMethod("sayHello").invoke(o));
		assertEquals("SELECT\n\tNAME, AGE\nFROM CUSTOMER\n", align(sayHelloWord));

		final String sayHello2Word = String.valueOf(clazz.getMethod("sayHello2").invoke(o));
		assertEquals("UPDATE TABLE SET NAME='MATEUS' WHERE ID = 5\n", align(sayHello2Word));

		assertEquals("SAY_HELLO_3\n", align(String.valueOf(clazz.getMethod("sayHello3").invoke(o))));

	}

	@Test
	public void shouldStillCompileOnClassWhoHasLambdas() throws Exception {

		// arrange
		final InMemoryJavaCompiler compiler = InMemoryJavaCompiler.newInstance();


		final String sourceCode = IOUtils.toString(getClass().getResourceAsStream("/ClassWithLambdaExpression.java"));

		// act
		final Class clazz = compiler.compile("ClassWithLambdaExpression", sourceCode);

		// assert
		final Object o = clazz.newInstance();
		final String selectQuery = (String) clazz.getMethod("sayHello").invoke(o);
		assertEquals("SELECT\n\tNAME, AGE\nFROM CUSTOMER\n", align(selectQuery));

	}

	// not supported yet
	@Test
	@Ignore
	public void shouldCompileVarInsideLambda() throws Exception {

		// arrange
		final InMemoryJavaCompiler compiler = InMemoryJavaCompiler.newInstance();


		final String sourceCode = IOUtils.toString(getClass().getResourceAsStream("/VariableInsideLambda.java"));

		// act
		final Class clazz = compiler.compile("VariableInsideLambda", sourceCode);
		final Object o = clazz.newInstance();
		final String selectQuery = (String) clazz.getMethod("sayHello").invoke(o);

		// assert
		assertEquals("SELECT\n\tNAME, AGE\nFROM CUSTOMER\n", align(selectQuery));

	}

	// not supported yet
	@Test
	@Ignore
	public void shouldCompileInstanceField() throws Exception {

		// arrange
		final InMemoryJavaCompiler compiler = InMemoryJavaCompiler.newInstance();


		final String sourceCode = IOUtils.toString(getClass().getResourceAsStream("/InstanceFieldClass.java"));

		// act
		final Class clazz = compiler.compile("InstanceFieldClass", sourceCode);
		final Object o = clazz.newInstance();
		final String selectQuery = (String) clazz.getMethod("sayHello").invoke(o);

		// assert
		assertEquals("SELECT\n\tNAME, AGE\nFROM CUSTOMER\n", align(selectQuery));

	}

}
