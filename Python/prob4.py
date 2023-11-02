import sys


def analyze(log_file_path, average_duration_path, top_requests_path):
    average_file = open(average_duration_path, 'w')
    top_file = open(top_requests_path, 'w')
    with open(log_file_path, 'r') as f:
        map = {}
        top_list = []
        for line in f:
            line = line.strip()
            strs = line.split(' ')
            # print(strs)
            id, key, timeCost = strs[0], strs[1], int(strs[2][:-2])
            if key not in map:
                map[key] = [0, 0]
            map[key][0] += 1
            map[key][1] += timeCost
            top_list.append([id, key, timeCost])
        top_list.sort(key=lambda x: x[2], reverse=True)
        # print(top_list)
        keys_list = list(map.keys())
        keys_list.sort()
        # print(keys_list)
        for key in keys_list:
            value = map[key]
            average_cost = format(value[1]/value[0], '.2f')
            average_file.write(str(key) + ": " + str(average_cost) + "ms\n")
        for i in range(3):
            top_file.write(str(top_list[i][0]) + " " + str(top_list[i][1]) + " " + str(top_list[i][2]) + "ms\n")
    average_file.close()
    top_file.close()


if __name__ == '__main__':
    analyze('abc.txt', 'average.txt', 'top.txt')
