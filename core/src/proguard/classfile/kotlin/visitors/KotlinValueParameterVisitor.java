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
package proguard.classfile.kotlin.visitors;

import proguard.classfile.Clazz;
import proguard.classfile.kotlin.*;

public interface KotlinValueParameterVisitor
{
    void visitAnyValueParameter(Clazz                        clazz,
                                KotlinValueParameterMetadata kotlinValueParameterMetadata);

    default void visitConstructorValParameter(Clazz                        clazz,
                                              KotlinClassKindMetadata      kotlinClassKindMetadata,
                                              KotlinConstructorMetadata    kotlinConstructorMetadata,
                                              KotlinValueParameterMetadata kotlinValueParameterMetadata)
    {
        visitAnyValueParameter(clazz, kotlinValueParameterMetadata);
    }

    default void visitFunctionValParameter(Clazz                        clazz,
                                           KotlinMetadata               kotlinMetadata,
                                           KotlinFunctionMetadata       kotlinFunctionMetadata,
                                           KotlinValueParameterMetadata kotlinValueParameterMetadata)
    {
        visitAnyValueParameter(clazz, kotlinValueParameterMetadata);
    }

    /**
     * Visit a value parameter of the property setter, if it has one.
     */
    default void visitPropertyValParameter(Clazz                              clazz,
                                           KotlinDeclarationContainerMetadata kotlinDeclarationContainerMetadata,
                                           KotlinPropertyMetadata             kotlinPropertyMetadata,
                                           KotlinValueParameterMetadata       kotlinValueParameterMetadata)
    {
        visitAnyValueParameter(clazz, kotlinValueParameterMetadata);
    }

    default void onNewFunctionStart() {}
}
