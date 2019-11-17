/*
 * ProGuard Core -- library to process Java bytecode.
 *
 * Copyright (c) 2002-2019 Guardsquare NV
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package proguard.classfile.visitor;

import proguard.classfile.*;


/**
 * This class visits ProgramMember objects referring to fields, identified by
 * a name and descriptor pair.
 *
 * @author Eric Lafortune
 */
public class NamedFieldVisitor implements ClassVisitor
{
    private final String        name;
    private final String        descriptor;
    private final MemberVisitor memberVisitor;


    public NamedFieldVisitor(String        name,
                             String        descriptor,
                             MemberVisitor memberVisitor)
    {
        this.name          = name;
        this.descriptor    = descriptor;
        this.memberVisitor = memberVisitor;
    }


    // Implementations for ClassVisitor.

    public void visitProgramClass(ProgramClass programClass)
    {
        programClass.fieldAccept(name, descriptor, memberVisitor);
    }


    public void visitLibraryClass(LibraryClass libraryClass)
    {
        libraryClass.fieldAccept(name, descriptor, memberVisitor);
    }
}
