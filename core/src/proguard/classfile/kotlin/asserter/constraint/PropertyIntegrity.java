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
package proguard.classfile.kotlin.asserter.constraint;

import proguard.classfile.Clazz;
import proguard.classfile.kotlin.*;
import proguard.classfile.kotlin.asserter.*;
import proguard.classfile.kotlin.visitors.KotlinPropertyVisitor;

/**
 * This class checks the assumption: All properties need a JVM signature for their getter
 */
public class PropertyIntegrity
extends    SimpleConstraintChecker
implements ConstraintChecker,
           KotlinPropertyVisitor
{
    public static KotlinMetadataConstraint constraint()
    {
        return KotlinMetadataConstraint.makeFromProperty(new PropertyIntegrity());
    }


    // Implementations for KotlinPropertyVisitor.
    @Override
    public void visitAnyProperty(Clazz                              clazz,
                                 KotlinDeclarationContainerMetadata kotlinDeclarationContainerMetadata,
                                 KotlinPropertyMetadata             kotlinPropertyMetadata)
    {
        AssertUtil util = new AssertUtil("Property", clazz, kotlinDeclarationContainerMetadata, reporter);

        if (kotlinPropertyMetadata.backingFieldSignature == null &&
            kotlinPropertyMetadata.getterSignature       == null &&
            kotlinPropertyMetadata.setterSignature       == null)
        {
            reporter.report(new MyMissingMetadataError("accessor",
                                                       clazz,
                                                       kotlinDeclarationContainerMetadata));
        }

        if (kotlinPropertyMetadata.backingFieldSignature  != null)
        {
            util.reportIfNullReference(kotlinPropertyMetadata.referencedBackingFieldClass,
                                             "backing field class");

            util.reportIfNullReference(kotlinPropertyMetadata.referencedBackingField,
                                             "backing field");

            if (kotlinPropertyMetadata.referencedBackingFieldClass != null &&
                kotlinPropertyMetadata.referencedBackingField      != null)
            {
                util.reportIfFieldDangling(kotlinPropertyMetadata.referencedBackingFieldClass,
                                                 kotlinPropertyMetadata.referencedBackingField,
                                                 "backing field");
            }
        }

        if (kotlinPropertyMetadata.getterSignature != null)
        {
            util.reportIfNullReference(kotlinPropertyMetadata.referencedGetterMethod,
                                       "getter");
        }

        if (kotlinPropertyMetadata.setterSignature != null)
        {
            util.reportIfNullReference(kotlinPropertyMetadata.referencedSetterMethod,
                                       "setter");
        }

        if (kotlinPropertyMetadata.syntheticMethodForAnnotations != null)
        {
            util.reportIfNullReference(kotlinPropertyMetadata.referencedSyntheticMethodClass,
                                       "synthetic annotations method class");

            util.reportIfNullReference(kotlinPropertyMetadata.referencedSyntheticMethodForAnnotations,
                                       "synthetic annotations method");


            if (kotlinPropertyMetadata.referencedSyntheticMethodClass          != null &&
                kotlinPropertyMetadata.referencedSyntheticMethodForAnnotations != null)
            {
                util.reportIfMethodDangling(kotlinPropertyMetadata.referencedSyntheticMethodClass,
                                            kotlinPropertyMetadata.referencedSyntheticMethodForAnnotations,
                                            "synthetic annotations method");
            }
        }
    }


    // Small helper classes.

    private static class MyMissingMetadataError
    extends MissingMetadataError
    {
        MyMissingMetadataError(String                             missingElement,
                               Clazz                              clazz,
                               KotlinDeclarationContainerMetadata kotlinDeclarationContainerMetadata)
        {
            super("Property", missingElement, clazz, kotlinDeclarationContainerMetadata);
        }
    }
}
