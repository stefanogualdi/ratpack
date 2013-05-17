/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ratpackframework.file;

import java.io.File;

/**
 * A file system binding represents a file system location that is used to resolve relative paths.
 *
 * Every exchange has a file system binding available via its context, as every Ratpack app has a file system binding.
 * <p>
 * The file system binding is used by asset serving handlers, among other places.
 *
 * @see org.ratpackframework.routing.Handlers#fsContext(String, org.ratpackframework.util.Action)
 * @see org.ratpackframework.routing.Handlers#assets(String, org.ratpackframework.routing.Handler)
 */
public interface FileSystemBinding {

  /**
   * The actual point on the filesystem that this binding is bound to.
   *
   * @return The actual point on the filesystem that this binding is bound to.
   */
  File getFile();

  /**
   * Creates a file reference relative to the bind point denoted by the given relative path.
   *
   * Absolute paths are resolved relative to the bind point, not the filesystem root.
   *
   * @param path The relative path from this binding to the desired file
   * @return The file
   */
  File file(String path);

  /**
   * Construct a new binding by using the given path as a relative path from this bind point.
   *
   * Absolute paths are resolved relative to the bind point, not the filesystem root.
   * <p>
   * Prefer using {@link org.ratpackframework.routing.Exchange#file(String)}.
   *
   * @param path The relative path from this binding to the desired binding
   * @return The binding
   */
  FileSystemBinding binding(String path);

  /**
   * Construct a new binding at the given file.
   *
   * Prefer using {@link org.ratpackframework.routing.Exchange#file(String)}.
   *
   * @param file The file of the desired binding
   * @return The binding
   */
  FileSystemBinding binding(File file);

}