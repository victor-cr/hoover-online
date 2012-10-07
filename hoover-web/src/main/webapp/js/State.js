/**
 * @param {number} width
 * @param {number} height
 * @param {number} x
 * @param {number} y
 * @param {number} capacity
 * @param {Array<number>} bag
 * @param {Array<Array<number>>} field
 * @constructor
 * @public
 */
var State = function (width, height, x, y, capacity, bag, field) {
    /** @type {number} */
    this.width = width;
    /** @type {number} */
    this.height = height;
    /** @type {number} */
    this.x = x;
    /** @type {number} */
    this.y = y;
    /** @type {number} */
    this.capacity = capacity;
    /** @type {Array<number>} */
    this.bag = bag;
    /** @type {Array<Array<number>>} */
    this.field = field;
};
