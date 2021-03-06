/*******************************************************************************
 * Copyright (c) 2015 Jeff Martin.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public
 * License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * Contributors:
 *     Jeff Martin - initial API and implementation
 ******************************************************************************/

package cuchaz.enigma;

import com.google.common.collect.Lists;
import cuchaz.enigma.translation.representation.entry.ClassEntry;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.jar.JarFile;

import static org.junit.Assert.assertEquals;

public class TestDeobfuscator {

	private Deobfuscator getDeobfuscator()
		throws IOException {
		return new Deobfuscator(new JarFile("build/test-obf/loneClass.jar"));
	}

	@Test
	public void loadJar()
		throws Exception {
		getDeobfuscator();
	}

	@Test
	public void getClasses()
		throws Exception {
		Deobfuscator deobfuscator = getDeobfuscator();
		List<ClassEntry> obfClasses = Lists.newArrayList();
		List<ClassEntry> deobfClasses = Lists.newArrayList();
		deobfuscator.getSeparatedClasses(obfClasses, deobfClasses);
		assertEquals(1, obfClasses.size());
		assertEquals("a", obfClasses.get(0).getName());
		assertEquals(1, deobfClasses.size());
		assertEquals("cuchaz/enigma/inputs/Keep", deobfClasses.get(0).getName());
	}

	@Test
	public void decompileClass()
		throws Exception {
		Deobfuscator deobfuscator = getDeobfuscator();
		SourceProvider sourceProvider = deobfuscator.getObfSourceProvider();
		sourceProvider.writeSourceToString(sourceProvider.getSources("a"));
	}
}
