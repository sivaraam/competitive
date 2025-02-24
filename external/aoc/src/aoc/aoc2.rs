use std::{collections::HashMap, fs::read_to_string};

pub fn func1()-> i32 {
    let mut list1: Vec<i32> = vec![];
    let mut mapp: HashMap<i32, i32> = HashMap::new();
    let filename = "./src/aoc/aoc1_input.txt";
    for line in read_to_string(filename).unwrap().lines() {
        let mut itr = str::split_ascii_whitespace(line);
        list1.push(itr.next().unwrap().parse().unwrap());
        mapp.entry(itr.next().unwrap().parse().unwrap()).and_modify(|x| *x += 1).or_insert(1);
    }
    let mut sum = 0;
    for x in list1 {
        sum += x * mapp.get(&x).unwrap_or(&0);
    }
    sum
}