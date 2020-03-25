with text_io; use text_io;

procedure number_test is

    type type_tag is (invalid, integral, floating);

    type number (tag: type_tag := invalid) is record
        case tag is
            when invalid => null;
            when integral => value_i: integer;
            when floating => value_f: float;
        end case;
    end record;

    a, b: number;

    procedure print (n: in number) is
        package int_io is new integer_io(integer);
        package flt_io is new float_io(float);
    begin
        case n.tag is
            when invalid => put(" <<< invalid number in ""print"" >>> ");
            when integral => int_io.put(n.value_i, 0);
            when floating => flt_io.put(n.value_f, 0, 0, 0);
        end case;
    end;

    function "+" (l, r: in number) return number is
        result: number := (tag=>invalid);
    begin
        -- more code here

        return result;
    end;

begin
    a := (integral, 5);                             -- a is integral
    b := (integral, 6);                             -- so is b

    put("a = "); print(a); new_line;
    put("b = "); print(b); new_line;
    put("a + b = "); print(a + b); new_line;        -- OK: both integral

    a := (floating, 5.7);                           -- a is now floating
    put("a = "); print(a); new_line;
    put("a + b = "); print(a + b); new_line;        -- type mismatch expected

    b := (floating, 3.2);                           -- b is now floating
    put("b = "); print(b); new_line;
    put("a + b = "); print(a + b); new_line;        -- OK: both floating
end number_test;
