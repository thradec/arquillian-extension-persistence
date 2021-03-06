/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.integration.persistence.datasource.deployment;

import org.jboss.arquillian.container.test.spi.client.deployment.AuxiliaryArchiveAppender;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

public class MsSqlDriver implements AuxiliaryArchiveAppender
{

   @Override
   public Archive<?> createAuxiliaryArchive()
   {
      final MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class)
                                                                  .loadMetadataFromPom("pom.xml")
                                                                  .goOffline();
      // Version needs to be specified explicitly because the artifact is defined for the profile
      final MavenDependencyResolver sqlServerArtifact = resolver.artifact("com.microsoft.sqlserver:sqljdbc4:4.0");

      return ShrinkWrap.createFromZipFile(JavaArchive.class, sqlServerArtifact.resolveAsFiles()[0]);
   }

}
