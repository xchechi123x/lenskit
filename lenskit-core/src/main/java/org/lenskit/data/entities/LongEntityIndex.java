/*
 * LensKit, an open source recommender systems toolkit.
 * Copyright 2010-2014 LensKit Contributors.  See CONTRIBUTORS.md.
 * Work on LensKit has been funded by the National Science Foundation under
 * grants IIS 05-34939, 08-08692, 08-12148, and 10-17697.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package org.lenskit.data.entities;

import com.google.common.collect.ImmutableList;
import org.lenskit.util.keys.SortedKeyIndex;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/**
 * Generic implementation of the entity index.
 */
class LongEntityIndex implements EntityIndex {
    private final SortedKeyIndex attributeValues;
    private final ImmutableList<ImmutableList<Entity>> entityLists;

    LongEntityIndex(SortedKeyIndex keys, ImmutableList<ImmutableList<Entity>> lists) {
        attributeValues = keys;
        entityLists = lists;
    }

    @Nonnull
    @Override
    public List<Entity> getEntities(@Nonnull Object value) {
        if (!(value instanceof Long)) {
            return Collections.emptyList();
        }
        Long lv = (Long) value;
        int idx = attributeValues.tryGetIndex(lv);
        if (idx >= 0) {
            return entityLists.get(idx);
        } else {
            return Collections.emptyList();
        }
    }
}