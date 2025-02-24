use std::{cmp::Ordering, fs::read_to_string, iter::Map, slice::Iter, str::SplitAsciiWhitespace};

fn under_limit(x: i32, y: i32) -> bool {    
    (x - y).abs() < 4 && (x - y).abs() > 0
}

fn is_sequence_safe<'a, T>(mut itr: T) -> bool 
where
    T: Iterator<Item = &'a str>
{
    let f : i32 = itr.next().unwrap().parse().unwrap();
    let mut s : i32 = itr.next().unwrap().parse().unwrap();
    if !under_limit(f, s) {
        return false;
    }
    let ord = s.cmp(&f);
    match ord {
        Ordering::Greater => {
            for x in itr {
                let z = x.parse().unwrap();
                if !under_limit(s, z) {
                    return false;
                }
                if z.cmp(&s) != Ordering::Greater {
                    return false;
                }
                s = z;
            }
        },
        Ordering::Less => {
            for x in itr {
                let z = x.parse().unwrap();
                if !under_limit(s, z) {
                    return false;
                }
                if z.cmp(&s) != Ordering::Less {
                    return false;
                }
                s = z;
            }        
        },
        Ordering::Equal => {
            return false;
        }
    }
    true
}

pub fn func1()-> i32 {
    let mut count: i32 = 0;

    let filename = "./src/aoc/aoc3_input.txt";
    for line in read_to_string(filename).unwrap().lines() {
        let itr = str::split_ascii_whitespace(line);
        let v : Vec<_> = itr.collect();
        let mut vv = false;
        for ii in 0..v.len() {
            let itr2 = v.iter().enumerate().filter(move |&(i, _)| i != ii).map(|(_, &x)| x);
            if is_sequence_safe(itr2) {
                vv = true;
            }
        }
        if vv {
            count += 1;
        }
    }
    count
}