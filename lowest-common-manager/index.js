function getLowestCommonManager(topManager, reportOne, reportTwo) {
  let depthOne = null;
  let depthTwo = null;

  //bfs to get the depth for each, and add the parent
  const queue = [{ parent: null, employee: topManager }];
  let depth = 0;

  while (queue.length && depthOne === null && depthTwo === null) {
    let numNodes = queue.length;
    depth++;
    while (numNodes > 0) {
      const { employee, parent } = queue.shift();
      numNodes--;
      employee.parent = parent;
      employee.depth = depth;

      for (const report of employee.directReports) {
        queue.push({ employee: report, parent: employee });
      }
    }
  }

  //account for the offset
  while (reportOne.depth > reportTwo.depth) {
    reportOne = reportOne.parent;
  }

  while (reportTwo.depth > reportOne.depth) {
    reportTwo = reportTwo.parent;
  }

  //traverse up
  while (reportOne.name !== reportTwo.name) {
    reportOne = reportOne.parent;
    reportTwo = reportTwo.parent;
  }

  return reportOne;
}

//OrgChart class has properties name and directReports
function OrgChart(name, directReports) {
  this.name = name;
  this.directReports = directReports;
  return this;
}

// Do not edit the line below.
exports.getLowestCommonManager = getLowestCommonManager;

const h = new OrgChart('H', []);
const i = new OrgChart('I', []);

const d = new OrgChart('D', [h, i]);
const e = new OrgChart('E', []);
const f = new OrgChart('F', []);
const g = new OrgChart('G', []);

const b = new OrgChart('B', [d, e]);
const c = new OrgChart('C', [f, g]);

const a = new OrgChart('A', [b, c]);

console.log(getLowestCommonManager(a, e, i));


