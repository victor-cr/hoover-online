/**
 * Created with IntelliJ IDEA.
 * User: id967092
 * Date: 07/10/12
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
$(document).ready(function() {
    window.hooverField = new Field("hooverFieldWrapper");

    window.hooverField.createField();

    $("#hooverFieldWrapper td.work").hover(function() {
        $(this).addClass("hovered");
    }, function() {
        $(this).removeClass("hovered");
    });

    $("#hooverFieldWrapper td.work").click(function() {
        var $this = $(this);

        var $parent = $this.parent();

        var x = $parent.children().index($this) - 1;
        var y = $parent.parent().children().index($parent) - 1;

        window.hooverField.setHoover(x, y);
    });

    $("#sendProgram").click(function() {
        var code = $("#programContent").val();
        var $log = $("#logWindow");

        HooverRemote.execute(window.hooverField.state, code, function(data) {
            window.hooverField.execute(0, data);
        });
    });

    $("#randomFill").click(function() {
        window.hooverField.random(10, 5);
    });
});