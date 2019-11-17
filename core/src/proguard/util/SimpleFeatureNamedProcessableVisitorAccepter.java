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
package proguard.util;

/**
 * A SimpleProcessableVisitorAccepter that additionally implements
 * FeatureNamed.
 *
 * @author Eric Lafortune
 */
public class SimpleFeatureNamedProcessableVisitorAccepter
extends      SimpleProcessableVisitorAccepter
implements   FeatureNamed
{
    /**
     * An extra field in which visitors can store a feature name.
     */
    public String featureName;


    public SimpleFeatureNamedProcessableVisitorAccepter() {}


    public SimpleFeatureNamedProcessableVisitorAccepter(int processingFlags)
    {
        super(processingFlags);
    }


    // Implementations for FeatureNamed.

    @Override
    public String getFeatureName()
    {
        return featureName;
    }

    @Override
    public void setFeatureName(String featureName)
    {
        this.featureName = featureName;
    }
}