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
package proguard.io;

import java.io.IOException;


/**
 * This interface provides a method to pump data entries. The implementation
 * determines the source and the type of the data entries. Typical examples
 * are zip entries coming from a zip file of file entries coming from a
 * directory structure. The reader can for instance collect the classes,
 * or copy the resource files that are presented.
 *
 * @author Eric Lafortune
 */
public interface DataEntryPump
{
    /**
     * Applies the given DataEntryReader to all data entries that the
     * implementation can provide.
     */
    public void pumpDataEntries(DataEntryReader dataEntryReader)
    throws IOException;
}
