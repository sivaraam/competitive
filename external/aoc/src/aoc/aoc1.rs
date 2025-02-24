use std::fs::read_to_string;

pub fn func1()-> i32 {
    let mut list1: Vec<i32> = vec![];
    let mut list2: Vec<i32> = vec![];
    let filename = "./src/aoc/aoc1_input.txt";
    for line in read_to_string(filename).unwrap().lines() {
        let mut itr = str::split_ascii_whitespace(line);
        list1.push(itr.next().unwrap().parse().unwrap());
        list2.push(itr.next().unwrap().parse().unwrap());
    }
    list1.sort();
    list2.sort();
    let mut sum = 0;
    for (x, y) in list1.iter().zip(list2.iter()) {
        sum += i32::abs(x-y);
    }
    sum
}