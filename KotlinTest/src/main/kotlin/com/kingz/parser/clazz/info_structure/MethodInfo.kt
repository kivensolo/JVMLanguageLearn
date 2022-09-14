package com.kingz.parser.clazz.info_structure

/**
 * The structure has the following format:
 *  method_info {
 *      u2             access_flags;
 *      u2             name_index;
 *      u2             descriptor_index;
 *      u2             attributes_count;
 *      attribute_info attributes[attributes_count];
 *  }
 *  Same as field_info.
 */
class MethodInfo : FieldInfo()