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
 * This ClassVisitor delegates all visits to a given ClassVisitor, although
 * only once to the same class in a row.
 *
 * @author Eric Lafortune
 */
public class SingleTimeClassVisitor implements ClassVisitor
{
    private final ClassVisitor classVisitor;

    private Clazz lastVisitedClass;


    public SingleTimeClassVisitor(ClassVisitor classVisitor)
    {
        this.classVisitor = classVisitor;
    }


    // Implementations for ClassVisitor.

    public void visitProgramClass(ProgramClass programClass)
    {
        if (!programClass.equals(lastVisitedClass))
        {
            classVisitor.visitProgramClass(programClass);

            lastVisitedClass = programClass;
        }
    }


    public void visitLibraryClass(LibraryClass libraryClass)
    {
        if (!libraryClass.equals(lastVisitedClass))
        {
            classVisitor.visitLibraryClass(libraryClass);

            lastVisitedClass = libraryClass;
        }
    }
}
