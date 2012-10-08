/**
 * @param {string} wrapperId
 * @constructor
 * @public
 */
var Field = function (wrapperId) {
    /** @type {string} */
    this.wrapperId = wrapperId;
    /** @type {jQuery} */
    this.$wrapper = $("#" + wrapperId);
    /** @type {!State} */
    this.state = new State(10, 10, 0, 0, 10, [], []);
};

Field.ID = "mainFieldTable";

/**
 * @return {!Field}
 * @public
 */
Field.prototype.createField = function () {
    this.$wrapper.children().remove();

    var $table = $("<table id='" + Field.ID + "'/>");

    this.$wrapper.append($table);

    var width = this.state.width;
    var height = this.state.height;

    for (var i = -1; i <= width; i++) {
        var $tr = $("<tr/>");

        $table.append($tr);

        var outX = i < 0 || i >= width;

        for (var j = -1; j <= height; j++) {
            var clazz, outY = j < 0 || j >= width;

            if (outX && outY) {
                clazz = "corner";
            } else if (outX) {
                clazz = "x-side";
            } else if (outY) {
                clazz = "y-side";
            } else {
                clazz = "work";
            }

            if (i === this.state.x && j === this.state.y) {
                clazz += " hoover";
            }

            var $td = $("<td class='" + clazz + "'/>");

            $tr.append($td);
        }
    }

    return this;
};

/**
 * @param {number} x
 * @param {number} y
 */
Field.prototype.setHoover = function (x, y) {
    this.state.x = x;
    this.state.y = y;

    $(".hoover").removeClass("hoover");
    this.getJQueryCell(x, y).addClass("hoover");
};

/**
 * @param {number} x
 * @param {number} y
 * @param {number?} item
 */
Field.prototype.setCell = function (x, y, item) {
    var field = this.state.field;

    var row = field[y] || (field[y] = []);

    row[x] = item;

    var cell = this.getJQueryCell(x, y);

    if (item || item === 0) {
        cell.text(item);
    } else {
        cell.text("");
    }
};

Field.prototype.push = function () {
    var state = this.state;

    var x = state.x;
    var y = state.y;
    var bag = state.bag;
    var field = state.field;

    var item = field[y][x];

    bag[bag.length] = item;
    this.setCell(state.x, state.y, null);

    var $bag = $("#bagWindow");

    var $item = $("<div/>").text(item);

    $bag.prepend($item);
};

Field.prototype.pop = function () {
    var state = this.state;

    var x = state.x;
    var y = state.y;
    var bag = state.bag;
    var field = state.field;

    var item = bag[bag.length - 1];

    bag.length--;

    this.setCell(state.x, state.y, item);

    $("#bagWindow").children().eq(0).remove();
};

/**
 * @param {number} offset
 * @param {Array<string>} commands
 */
Field.prototype.execute = function (offset, commands) {
    var self = this;
    var state = this.state;
    var command = commands[offset];
    var cmd = command.substr(0, 1).toUpperCase();
    var param = command.substr(1);

    if (offset >= commands.length) {
        return;
    }

    var $log = $("#logWindow textarea");
    $log.text(command + "\n" + $log.text());

    switch (cmd) {
        case "U" :
            this.setHoover(state.x, state.y - 1);
            break;
        case "D" :
            this.setHoover(state.x, state.y + 1);
            break;
        case "L" :
            this.setHoover(state.x - 1, state.y);
            break;
        case "R" :
            this.setHoover(state.x + 1, state.y);
            break;
        case "+" :
            alert(param);
            break;
        case "-" :
            alert(param);
            return;
        case "T" :
            this.push();
            break;
        case "P" :
            this.pop();
            break;
    }

    setTimeout(function () {
        self.execute(offset + 1, commands);
    }, 100);
};

/**
 * @param {number} count
 * @param {number} range
 */
Field.prototype.random = function (count, range) {
    var state = this.state;

    for (var i = 0; i < count; i++) {
        var x = Math.floor(Math.random() * state.width);
        var y = Math.floor(Math.random() * state.height);
        var item = Math.floor(Math.random() * range);

        this.setCell(x, y, item);
    }
};

/**
 * @param {number} x
 * @param {number} y
 * @return {jQuery}
 * @private
 */
Field.prototype.getJQueryCell = function (x, y) {
    return $("#" + Field.ID + " tr").eq(y + 1).children("td.work").eq(x);
};
